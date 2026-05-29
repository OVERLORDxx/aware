-- Run this in MySQL Workbench or terminal before starting the app
-- Command: mysql -u root -p < database.sql

CREATE DATABASE IF NOT EXISTS aware_db;
USE aware_db;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(10)  NOT NULL DEFAULT 'user'  -- 'user' or 'admin'
);

-- Reports table
CREATE TABLE IF NOT EXISTS reports (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    issue_type      VARCHAR(100) NOT NULL,
    description     TEXT         NOT NULL,
    location        VARCHAR(255),
    status          VARCHAR(20)  NOT NULL DEFAULT 'PENDING',
    hash            VARCHAR(64)  NOT NULL,
    previous_hash   VARCHAR(64)  NOT NULL,
    submitted_by    VARCHAR(50),
    submitter_email VARCHAR(100),
    image           LONGBLOB
);

-- Insert a default admin account
-- username: admin | password: admin123
INSERT INTO users (username, email, password, role)
VALUES ('admin', 'admin@aware.com', 'admin123', 'admin')
ON DUPLICATE KEY UPDATE id = id;
