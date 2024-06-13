create table PRODUCT
(
    ID           VARCHAR(36) PRIMARY KEY NOT NULL,
    NAME         VARCHAR(20)             NOT NULL,
    CREATED_DATE TIMESTAMP               NOT NULL,
    TYPE         VARCHAR(10)             NOT NULL
);