package mj.api.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MonthValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MonthValidation {
    String message() default "Invalid Month";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
