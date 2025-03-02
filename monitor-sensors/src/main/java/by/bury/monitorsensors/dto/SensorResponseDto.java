package by.bury.monitorsensors.dto;

import by.bury.monitorsensors.model.Range;

public record SensorResponseDto(
        String uuid,
        String name,
        String model,
        Range range,
        String type,
        String unit,
        String location,
        String description
) {
}
