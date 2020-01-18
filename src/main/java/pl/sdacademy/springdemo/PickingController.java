package pl.sdacademy.springdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdacademy.springdemo.services.PickingStreamProcessor;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/pick")
public class PickingController {

    private final PickingStreamProcessor pickingStreamProcessor;

    public PickingController(final PickingStreamProcessor pickingStreamProcessor) {
        this.pickingStreamProcessor = pickingStreamProcessor;
    }

    @GetMapping
    public void process() throws IOException {
        final String input = "{\"timestamp\":\"2018-12-20T11:50:48Z\",\"id\":\"2344\",\"picker\":{\"id\":\"14\",\"name\":\"Joris\",\"active_since\":\"2018-09-20T08:20:00Z\"},\"article\":{\"id\":\"13473\",\"name\":\"ACME Bananas\",\"temperature_zone\":\"ambient\"},\"quantity\":2}\n";
        final ByteArrayInputStream source = new ByteArrayInputStream(input.getBytes());
        pickingStreamProcessor.process(source, null);
    }
}
