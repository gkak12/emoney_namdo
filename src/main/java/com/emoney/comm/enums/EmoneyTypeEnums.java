package com.emoney.comm.enums;

public enum EmoneyTypeEnums {

    CREATION(1L, "적립금 발급"),
    USAGE(2L, "적립금 사용"),
    REDUCTION(3L, "적립금 차감"),
    EXPIRATION(4L, "적립금 만료"),
    EXTENTION(5L, "적립금 연장");

    private final Long val;
    private final String desc;

    private EmoneyTypeEnums(Long val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
