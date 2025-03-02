package by.bury.monitorsensors.annotation;

import by.bury.monitorsensors.exception.SensorNotFoundException;
import by.bury.monitorsensors.service.SensorService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsSensorValidator implements ConstraintValidator<IsSensorExist, String> {

    private final SensorService sensorService;

    public IsSensorValidator(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext constraintValidatorContext) {
        if (sensorService.isSensorExists(uuid)) {
            return true;
        } else {
            throw new SensorNotFoundException(String.format("Sensor with UUID %s not found!!!", uuid));
        }
    }
}
