package pl.sdacademy.springdemo.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.WillNotClose;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.springdemo.configuration.StreamProcessorConfiguration;
import pl.sdacademy.springdemo.exceptions.ProcessingException;
import pl.sdacademy.springdemo.model.PickEvent;
import pl.sdacademy.springdemo.model.PickerStatistics;

@Service
public class StreamProcessor {

  private static final long DATA_WAITING_TIME_IN_MILLIS = 5L;
  private static final int CHUNK_SIZE = 1;
  private static final byte EVENT_SERAPATOR = '\n';

  private final StreamProcessorConfiguration streamProcessorConfiguration;
  private final ObjectMapper objectMapper;
  private final PickEventAggregator pickEventAggregator;

  public StreamProcessor(final StreamProcessorConfiguration streamProcessorConfiguration, final ObjectMapper objectMapper,
                         final PickEventAggregator pickEventAggregator) {
    this.streamProcessorConfiguration = streamProcessorConfiguration;
    this.objectMapper = objectMapper;
    this.pickEventAggregator = pickEventAggregator;
  }

  public void process(@WillNotClose final InputStream source, @WillNotClose final OutputStream sink) throws IOException {
    final long startingTimestampInMillis = System.currentTimeMillis();
    final List<PickEvent> readEvents = new ArrayList<>();
    final List<Byte> singleEventData = new ArrayList<>();
    while(shouldContinueProcessing(startingTimestampInMillis, readEvents.size())) {
      if (source.available() == 0) {
        waitForAvailableData();
        continue;
      }
      final byte readByte = source.readNBytes(CHUNK_SIZE)[0];
      if (readByte != EVENT_SERAPATOR) {
        singleEventData.add(readByte);
      } else if (!singleEventData.isEmpty()) {
        readEvents.add(toPickEvent(singleEventData));
        singleEventData.clear();
      }
    }
    final List<PickerStatistics> statistics = pickEventAggregator.aggregate(readEvents);
    sink.write(toByteArray(statistics));
  }

  private boolean shouldContinueProcessing(final long startTimestampInMillis,
                                          final int processedEventsNum) {
    final long currentTimestamp = System.currentTimeMillis();
    return processedEventsNum < streamProcessorConfiguration.getMaxEventsNum() &&
        (currentTimestamp - startTimestampInMillis) < streamProcessorConfiguration.getMaxProcessingTime().toMillis();
  }

  private void waitForAvailableData() {
    try {
      Thread.sleep(DATA_WAITING_TIME_IN_MILLIS);
    } catch (final InterruptedException exp) {
      throw new ProcessingException("Failed to wait for new data", exp);
    }
  }

  private PickEvent toPickEvent(final List<Byte> eventBytes) throws IOException {
    return objectMapper.readValue(ArrayUtils.toPrimitive(eventBytes.toArray(new Byte[0])), PickEvent.class);
  }

  private byte[] toByteArray(final List<PickerStatistics> statistics) throws JsonProcessingException {
    return objectMapper.writeValueAsBytes(statistics);
  }
}
