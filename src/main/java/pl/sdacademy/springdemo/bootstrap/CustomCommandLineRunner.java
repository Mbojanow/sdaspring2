package pl.sdacademy.springdemo.bootstrap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.sdacademy.springdemo.service.StreamProcessor;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {

  private final StreamProcessor streamProcessor;

  public CustomCommandLineRunner(final StreamProcessor streamProcessor) {
    this.streamProcessor = streamProcessor;
  }

  @Override
  public void run(final String... args) throws Exception {
    String event = "{\"timestamp\":\"2018-12-20T11:50:48Z\",\"id\":\"2344\",\"picker\":{\"id\":\"14\",\"name\":\"Joris\",\"active_since\":\"2018-09-20T08:20:00Z\"},\"article\":{\"id\":\"13473\",\"name\":\"ACME Bananas\",\"temperature_zone\":\"ambient\"},\"quantity\":2}\n";
    final ByteArrayInputStream source = new ByteArrayInputStream(event.getBytes());
    final ByteArrayOutputStream sink = new ByteArrayOutputStream();
    streamProcessor.process(source, sink);
  }
}
