package by.bury.monitorsensors.dto;

import by.bury.monitorsensors.annotation.ValidRange;
import by.bury.monitorsensors.model.Range;
import by.bury.monitorsensors.model.SensorType;
import by.bury.monitorsensors.model.SensorUnit;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record SensorRequestDto(
        @NotEmpty(message = "The field name shouldn't be empty")
        @Size(min = 3, max = 30, message = "The field name should contain from 3 to 30 symbols")
        String name,

        @NotEmpty(message = "The field model shouldn't be empty")
        @Size(min = 1, max = 15, message = "The field name shouldn't contain more than 15 symbols")
        String model,

        @ValidRange
        Range range,

        @NotNull
        SensorType type,

        SensorUnit unit,

        @Size(max = 40, message = "The field location shouldn't contain more than 40 symbols")
        String location,

        @Size(max = 200, message = "The field description shouldn't contain more than 200 symbols")
        String description

) {


}
