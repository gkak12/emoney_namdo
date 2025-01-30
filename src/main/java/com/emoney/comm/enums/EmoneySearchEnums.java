package com.emoney.comm.enums;

import lombok.Getter;

@Getter
public enum EmoneySearchEnums {

    AMOUNT("amount", "초기에 받은 적립금입니다."),
    USAGE_AMOUNT("usageAmount", "지금까지 사용한 누적 적립금입니다."),
    REMAIN_AMOUNT("remainAmount", "지금까지 사용하고 남은 잔액 적립금입니다."),
    EXPIRATION_DATE("expirationDate", "적립금 만료일입니다."),
    CREATION_DATE("creationDate", "적립금 발급일입니다."),
    IS_APPROVED("isApproved", "적립금 승인 여부입니다."),
    IS_EXPIRED("isExpired", "적립금 만료 여부입니다.");

    private final String val;
    private final String desc;

    EmoneySearchEnums(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
