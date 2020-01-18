package pl.sdacademy.springdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickerStatistics {

    @JsonProperty("picker_name")
    private String name;

    @JsonProperty("active_since")
    private LocalDateTime activeSince;

    @JsonProperty("picks")
    private List<Pick> picks;
}
