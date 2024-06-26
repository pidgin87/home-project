create table OPERATION
(
    ID                   VARCHAR(36) PRIMARY KEY NOT NULL,

    SOURCE_PRODUCT       VARCHAR(36),
    SOURCE_AMOUNT        NUMERIC(10, 2),
    SOURCE_CURRENCY      VARCHAR(3),

    DESTINATION_PRODUCT  VARCHAR(36),
    DESTINATION_AMOUNT   NUMERIC(10, 2),
    DESTINATION_CURRENCY VARCHAR(3),

    OPERATION_DATE       TIMESTAMP,
    CREATED_DATE         TIMESTAMP
);