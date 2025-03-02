package by.bury.monitorsensors.annotation;

import by.bury.monitorsensors.model.Range;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<ValidRange, Range> {


    @Override
    public boolean isValid(Range range, ConstraintValidatorContext constraintValidatorContext) {
        if (range.getTo() == null ) {
            return false;
        } else if (range.getFrom() == null) {
            return true;
        }
        return range.getFrom() < range.getTo();
    }
}
