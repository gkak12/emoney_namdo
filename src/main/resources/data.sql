INSERT INTO EMONEY
(USER_SEQ, ORDER_SEQ, TYPE_SEQ, AMOUNT, USAGE_AMOUNT, REMAIN_AMOUNT, IS_APPROVED, CONTENT, EXPIRATION_DATE, CREATION_DATE)
VALUES
    (1, 0, 1, 10000, 10000, 0, TRUE, '회원가입 적립금', '2025-03-31 23:59:59', '2025-01-01 00:00:00'),
    (1, 1001, 2, 5000, 5000, 0, TRUE, '쌀 상품 구매', null, '2025-01-11 09:20:00'),
    (1, 0, 1, 5000, 2000, 3000, TRUE, '포토 리뷰 적립금', '2025-04-30 23:59:59', '2025-01-10 00:00:00'),
    (1, 1002, 2, 6000, 6000, 0, TRUE, '육류 상품 구매', null, '2025-02-01 17:50:00'),
    (1, 1001, 3, 5000, 0, 5000, TRUE, '쌀 상품 구매 취소', null, '2025-01-12 12:30:00'),
    (1, 0, 4, 1000, 1000, 0, TRUE, '적립금 차감', null, '2025-01-11 17:50:00')
;

INSERT INTO EMONEY_USAGE_HISTORY
(USAGE_TYPE_SEQ, USAGE_AMOUNT, CONTENT, EMONEY_SEQ, CREATION_DATE)
VALUES
    (1, 5000, '쌀 상품 구매', 1, '2025-01-11 09:20:00'),
    (1, 5000, '육류 상품 구매', 1, '2025-02-01 17:50:00'),
    (1, 1000, '육류 상품 구매', 3, '2025-02-01 17:50:00'),
    (2, 1000, '적립금 차감', 3, '2025-01-11 17:50:00')
;