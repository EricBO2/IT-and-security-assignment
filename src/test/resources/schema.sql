CREATE TABLE app_user (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        roles VARCHAR(255) NOT NULL
);
