CREATE TABLE user_profile
(
    id         VARCHAR(36) PRIMARY KEY NOT NULL,
    firstname  VARCHAR(50),
    lastname   VARCHAR(50),
    email      VARCHAR(50)             NOT NULL,
    company_id VARCHAR(50)             NOT NULL
);

ALTER TABLE user_profile ADD CONSTRAINT FK_COMPANY FOREIGN KEY (COMPANY_ID) REFERENCES company(ID);