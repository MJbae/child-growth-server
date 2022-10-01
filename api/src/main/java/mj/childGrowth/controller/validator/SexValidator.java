package mj.childGrowth.controller.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static mj.childGrowth.domain.Sex.FEMALE;
import static mj.childGrowth.domain.Sex.MALE;

public class SexValidator implements ConstraintValidator<SexValidation, String> {

    @Override
    public void initialize(SexValidation sex) {
    }

    @Override
    public boolean isValid(String sex, ConstraintValidatorContext cxt) {
        return sex != null
                && !sex.isBlank()
                && (sex.toUpperCase().equals(MALE.toString())
                    || sex.toUpperCase().equals(FEMALE.toString()));
    }

}
