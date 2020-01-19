package pl.sdacademy.springdemo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.sdacademy.springdemo.model.Pick;
import pl.sdacademy.springdemo.model.PickEvent;
import pl.sdacademy.springdemo.model.PickerStatistics;

@Service
public class PickEventAggregator {

  public List<PickerStatistics> aggregate(final List<PickEvent> pickEvents) {
    return pickEvents.stream()
        .filter(event -> event.getArticle().getTemperatureZone().equals("ambient"))
        .collect(Collectors.groupingBy(PickEvent::getPicker))
        .values()
        .stream()
        .map(this::toPickerStatistics)
        .sorted(Comparator.comparing(PickerStatistics::getActiveSince)
            .thenComparing(PickerStatistics::getPickerName, Comparator.reverseOrder()))
        .collect(Collectors.toList());
  }

  private PickerStatistics toPickerStatistics(final List<PickEvent> pickEvents) {
    final List<Pick> picks = pickEvents.stream()
        .map(pickEvent -> Pick.builder()
            .articleName(pickEvent.getArticle().getName().toUpperCase())
            .timestamp(pickEvent.getTimestamp())
            .build())
        .sorted(Comparator.comparing(Pick::getTimestamp).thenComparing(Pick::getArticleName))
        .collect(Collectors.toList());

    return PickerStatistics.builder()
        .activeSince(pickEvents.get(0).getPicker().getActiveSince())
        .pickerName(pickEvents.get(0).getPicker().getName())
        .picks(picks)
        .build();
  }
}
