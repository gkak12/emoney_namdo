package com.emoney.comm.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmoneySeqValidator implements ConstraintValidator<ValidEmoneySeq, Long> {

    @Override
    public void initialize(ValidEmoneySeq constraintAnnotation) {
        // 초기화 작업이 필요할 경우 작성 (없으면 비워두기)
    }

    @Override
    public boolean isValid(Long emoneySeq, ConstraintValidatorContext context) {
        // emoneySeq가 null이거나 1 미만인 경우 유효하지 않음
        return emoneySeq != null && emoneySeq >= 1;
    }
}
