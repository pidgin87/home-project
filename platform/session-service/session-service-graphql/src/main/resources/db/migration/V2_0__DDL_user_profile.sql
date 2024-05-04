CREATE TABLE user_profile_session
(
    id              VARCHAR(36) PRIMARY KEY NOT NULL,
    company_id      VARCHAR(36)             NOT NULL,
    user_profile_id VARCHAR(36)             NOT NULL
);