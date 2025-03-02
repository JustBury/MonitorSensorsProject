package by.bury.monitorsensors.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeValidator.class)
public @interface ValidRange {

    String message() default "Invalid range: 'from' must be less than 'to'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
