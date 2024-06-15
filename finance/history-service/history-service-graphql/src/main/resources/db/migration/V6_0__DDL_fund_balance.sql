create table FUND_BALANCE
(
    ID           VARCHAR(36) PRIMARY KEY NOT NULL,

    FUND_ID      VARCHAR(36)             NOT NULL,
    AMOUNT       NUMERIC(10, 5)          NOT NULL,
    CURRENCY     VARCHAR(36)             NOT NULL,

    OPERATION_ID VARCHAR(36)             NOT NULL REFERENCES OPERATION (ID)
);

