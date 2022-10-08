package mj.api.controller.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HeightValidator implements ConstraintValidator<HeightValidation, Float> {

    @Override
    public void initialize(HeightValidation height) {
    }

    @Override
    public boolean isValid(Float height, ConstraintValidatorContext cxt) {
        return height != null
                && !height.isInfinite()
                && !height.isNaN()
                && height > 0
                && height < 300;
    }

}
