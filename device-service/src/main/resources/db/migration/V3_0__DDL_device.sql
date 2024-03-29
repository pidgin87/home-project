create table DEVICE
(
    ID         VARCHAR(36) PRIMARY KEY NOT NULL,
    NAME       VARCHAR(50) UNIQUE      NOT NULL,
    ROOM_ID    VARCHAR(36),
    GLOBAL_ID  VARCHAR(36)             NOT NULL,
    PRODUCT_ID VARCHAR(36)             NOT NULL,
    CONSTRAINT FK_PRODUCT
        FOREIGN KEY(PRODUCT_ID)
            REFERENCES PRODUCT(ID)
);