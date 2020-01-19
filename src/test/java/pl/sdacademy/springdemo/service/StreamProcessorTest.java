package pl.sdacademy.springdemo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.springdemo.configuration.StreamProcessorConfiguration;
import pl.sdacademy.springdemo.model.PickEvent;

@ExtendWith(MockitoExtension.class)
class StreamProcessorTest {

  @Mock
  private StreamProcessorConfiguration streamProcessorConfiguration;

  @Mock
  private ObjectMapper objectMapper;

  @Mock
  private PickEventAggregator pickEventAggregator;

  @InjectMocks
  private StreamProcessor streamProcessor;

  @Mock
  private InputStream inputStream;

  @Test
  void shouldProcessInputAndOutput() throws IOException {
    final Duration time = Duration.ofSeconds(1L);
    final int maxEventsNum = 3;
    when(streamProcessorConfiguration.getMaxEventsNum()).thenReturn(maxEventsNum);
    when(streamProcessorConfiguration.getMaxProcessingTime()).thenReturn(time);
    int counter[] = {0};
    when(inputStream.available()).thenAnswer(invocationOnMock -> {
      if (counter[0] < 1000) {
        counter[0]++;
        return 1;
      }
      return 0;
    });
    int byteCounter[] = {0};
    when(inputStream.readNBytes(1)).thenAnswer(invocationOnMock -> {
      if (byteCounter[0] < 999) {
        return new byte[]{'a'};
      }
      return new byte[]{'\n'};
    });
    when(objectMapper.readValue(new byte[999]{'a'}, PickEvent.class))
  }

}