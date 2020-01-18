package pl.sdacademy.springdemo.services;

import org.springframework.stereotype.Component;
import pl.sdacademy.springdemo.model.Pick;
import pl.sdacademy.springdemo.model.PickEvent;
import pl.sdacademy.springdemo.model.Picker;
import pl.sdacademy.springdemo.model.PickerStatistics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PickEventAggregator {

    public List<PickerStatistics> aggregate(final List<PickEvent> pickEvents) {
        return pickEvents.stream()
                .filter(pickEvent -> pickEvent.getArticle().getTemperatureZone().equals("ambient"))
                .collect(Collectors.groupingBy(PickEvent::getPicker))
                .entrySet().stream()
                .map(this::toPickerStatistics)
                .sorted(Comparator.comparing(PickerStatistics::getActiveSince))
                .collect(Collectors.toList());
    }

    private PickerStatistics toPickerStatistics(final Map.Entry<Picker, List<PickEvent>> pickerNameToPickEvents) {
        final List<Pick> picks = pickerNameToPickEvents.getValue().stream().map(pickEvent ->
                Pick.builder()
                        .timestamp(pickEvent.getTimestamp())
                        .articleName(pickEvent.getArticle().getName())
                        .pickerId(pickEvent.getPicker().getId())
                        .build())
                .sorted(Comparator.comparing(Pick::getTimestamp)
                        .thenComparing(Pick::getPickerId))
                .collect(Collectors.toList());

        return PickerStatistics.builder()
                .activeSince(pickerNameToPickEvents.getKey().getActiveSince())
                .name(pickerNameToPickEvents.getKey().getName())
                .picks(picks)
                .build();
    }
}
