create table CURRENCY_DICT
(
    ID    VARCHAR(36) PRIMARY KEY NOT NULL,
    ISO   VARCHAR(3)              NOT NULL,
    NAME  VARCHAR(20)             NOT NULL,
    "ORDER" NUMERIC                 NOT NULL
);