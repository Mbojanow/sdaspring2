package pl.sdacademy.springdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickEvent {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("picker")
    private Picker picker;

    @JsonProperty("article")
    private Article article;

    @JsonProperty("quantity")
    private int quantity;
}
