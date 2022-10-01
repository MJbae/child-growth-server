package mj.childGrowth.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HeightValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface HeightValidation {
    String message() default "Invalid Height";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
