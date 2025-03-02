package by.bury.sensorstatisticsservice.dto;

import by.bury.sensorstatisticsservice.model.Range;

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
