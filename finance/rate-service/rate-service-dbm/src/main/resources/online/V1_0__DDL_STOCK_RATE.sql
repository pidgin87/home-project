create table STOCK_RATE
(
    ID            VARCHAR(36) PRIMARY KEY NOT NULL,
    TICKER        VARCHAR(10)             NOT NULL,
    CREATED_DATE  TIMESTAMP               NOT NULL,
    DICTIONARY_ID VARCHAR(36)             NOT NULL,
    VALUE         VARCHAR(10)             NOT NULL
);

create index IDX_STOCK_RATE_TICKER on STOCK_RATE (TICKER);

