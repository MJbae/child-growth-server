package mj.childGrowth.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SexValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SexValidation {
    String message() default "Invalid Sex";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
