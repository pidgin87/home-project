create table CURRENCY_PAIR_RATE
(
    ID            VARCHAR(36) PRIMARY KEY NOT NULL,
    TICKER        VARCHAR(10)             NOT NULL,
    CREATED_DATE  TIMESTAMP               NOT NULL,
    DICTIONARY_ID VARCHAR(36)             NOT NULL,
    VALUE         VARCHAR(10)             NOT NULL
);

create index IDX_CURRENCY_PAIR_RATE on CURRENCY_PAIR_RATE (TICKER);

