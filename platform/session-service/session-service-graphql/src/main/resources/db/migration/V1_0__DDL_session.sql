CREATE TABLE session
(
    id                      VARCHAR(36) PRIMARY KEY NOT NULL,
    user_profile_session_id VARCHAR(36)             NOT NULL,
    last_activity           TIMESTAMP               NOT NULL
);