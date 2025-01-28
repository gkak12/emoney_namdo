package com.emoney.comm.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmoneySeqValidator implements ConstraintValidator<ValidEmoneySeq, Long> {

    @Override
    public boolean isValid(Long emoneySeq, ConstraintValidatorContext context) {
        // emoneySeq가 null 이거나 1 미만인 경우 유효하지 않음
        return emoneySeq != null && emoneySeq > 0;
    }
}
