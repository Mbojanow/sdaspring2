package pl.sdacademy.springdemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import pl.sdacademy.springdemo.configuration.StreamProcessorConfiguration;
import pl.sdacademy.springdemo.model.PickEvent;
import pl.sdacademy.springdemo.model.PickerStatistics;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PickingStreamProcessor {

    private final ObjectMapper objectMapper;
    private final StreamProcessorConfiguration streamProcessorConfiguration;
    private final PickEventAggregator pickEventAggregator;

    public PickingStreamProcessor(final ObjectMapper objectMapper, final StreamProcessorConfiguration streamProcessorConfiguration, final PickEventAggregator pickEventAggregator) {
        this.objectMapper = objectMapper;
        this.streamProcessorConfiguration = streamProcessorConfiguration;
        this.pickEventAggregator = pickEventAggregator;
    }

    public void process(final InputStream source, final OutputStream sink) throws IOException {
        final long startTimestamp = System.currentTimeMillis();
        final List<PickEvent> eventsReadFromSource = new ArrayList<>();
        final List<Byte> singleEventData = new ArrayList<>();
        while (shouldContinueProcessing(startTimestamp, eventsReadFromSource.size())) {
            if (isDataUnavailable(source)) {
                waitForNewData();
                continue;
            }

            final byte readByte = source.readNBytes(1)[0];
            if (readByte == '\n' && !singleEventData.isEmpty()) {
                final PickEvent pickEvent = objectMapper.readValue(ArrayUtils.toPrimitive(singleEventData.toArray(new Byte[0])), PickEvent.class);
                eventsReadFromSource.add(pickEvent);
                singleEventData.clear();
            } else if (readByte != '\n') {
                singleEventData.add(readByte);
            }
        }
        final List<PickerStatistics> pickerStatistics = pickEventAggregator.aggregate(eventsReadFromSource);
        for (PickerStatistics pickerStatistic : pickerStatistics) {
            sink.write(objectMapper.writeValueAsBytes(pickerStatistic));
        }
    }

    private boolean shouldContinueProcessing(final long startTimestamp, final int processedEventsNum) {
        final long currentTimestamp = System.currentTimeMillis();
        return processedEventsNum < streamProcessorConfiguration.getMaxEventsNum() &&
                (currentTimestamp - startTimestamp) < TimeUnit.SECONDS.toMillis(streamProcessorConfiguration.getMaxProcessingTime().getSeconds());
    }

    private boolean isDataUnavailable(final InputStream source) {
        try {
            return source.available() == 0;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void waitForNewData() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
