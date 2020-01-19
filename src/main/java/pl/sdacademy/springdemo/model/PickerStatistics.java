package pl.sdacademy.springdemo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickerStatistics {

  @JsonProperty("picker_name")
  private String pickerName;

  @JsonProperty("active_since")
  private LocalDateTime activeSince;

  @JsonProperty("picks")
  private List<Pick> picks;
}
