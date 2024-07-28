CREATE TABLE USER_ROLES
(
    ID      VARCHAR(36) PRIMARY KEY NOT NULL,
    USER_ID VARCHAR(36)             NOT NULL,
    VALUE   VARCHAR(10)
);

ALTER TABLE USER_ROLES
    ADD CONSTRAINT FK_USER_ROLES FOREIGN KEY (USER_ID) REFERENCES USER_PROFILE (ID);