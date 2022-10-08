package mj.api.controller.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MonthValidator implements ConstraintValidator<MonthValidation, Integer> {

    @Override
    public void initialize(MonthValidation month) {
    }

    @Override
    public boolean isValid(Integer month, ConstraintValidatorContext cxt) {
        return month != null
                && month >= 0
                && month <= 227;
    }

}
