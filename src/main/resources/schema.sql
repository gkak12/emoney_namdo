CREATE TABLE EMONEY(
    EMONEY_SEQ BIGINT AUTO_INCREMENT PRIMARY KEY,       -- 적립금 SEQ
    USER_SEQ BIGINT,                                    -- 사용자 SEQ
    ORDER_SEQ BIGINT,                                   -- 주문 SEQ
    TYPE_SEQ BIGINT,                                    -- 적립금 타입 SEQ(회원가입, 텍스트 리뷰, 포토 리뷰, 이벤트, 구매 취소, 사용, 차감)
    AMOUNT BIGINT,                                      -- 적립금(초기 적립받은 금액)
    USAGE_AMOUNT BIGINT,                                -- 사용한 적립금
    REMAIN_AMOUNT BIGINT,                               -- 잔여 적립금
    IS_APPROVED BOOLEAN DEFAULT FALSE,                  -- 승인여부
    IS_EXPIRED BOOLEAN DEFAULT FALSE,                   -- 만료여부
    CONTENT VARCHAR(100),                               -- 내용
    EXPIRATION_DATE_TIME TIMESTAMP,                     -- 만료일시
    CREATION_DATE_TIME TIMESTAMP                        -- 생성일시
);

CREATE TABLE EMONEY_USAGE_HISTORY(
    EMONEY_USAGE_HISTORY_SEQ BIGINT AUTO_INCREMENT PRIMARY KEY,     -- 적립금 사용 SEQ
    USAGE_TYPE_SEQ BIGINT,                                          -- 적립금 사용 타입 SEQ(사용, 차감)
    USAGE_AMOUNT BIGINT,                                            -- 사용한 적립금
    CONTENT VARCHAR(100),                                           -- 내용
    CREATION_DATE_TIME TIMESTAMP,                                   -- 생성일시
    EMONEY_SEQ BIGINT,                                              -- 적립금 SEQ
    FOREIGN KEY (EMONEY_SEQ) REFERENCES EMONEY(EMONEY_SEQ)          -- 적립금 참조키
);