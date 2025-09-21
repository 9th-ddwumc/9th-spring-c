-- Users 테이블
CREATE TABLE Users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NULL,
    preference VARCHAR(100) NULL,
    points INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    phone_number VARCHAR(20) NULL,
    phone_verified BOOLEAN NOT NULL DEFAULT FALSE
);

-- Point_Log 테이블
CREATE TABLE Point_Log (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    points INT NOT NULL,
    reason VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Regions 테이블
CREATE TABLE Regions (
    region_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    region_name VARCHAR(100) NOT NULL
);

-- Stores 테이블
CREATE TABLE Stores (
    store_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    region_id BIGINT NOT NULL,
    store_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NULL,
    FOREIGN KEY (region_id) REFERENCES Regions(region_id)
);

-- Missions 테이블
CREATE TABLE Missions (
    mission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    store_id BIGINT NOT NULL,
    mission_title VARCHAR(100) NOT NULL,
    mission_desc TEXT NULL,
    reward_point INT NULL,
    FOREIGN KEY (store_id) REFERENCES Stores(store_id)
);

-- Mission_Assignments 테이블
CREATE TABLE Mission_Assignments (
    assignment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    mission_id BIGINT NOT NULL,
    status ENUM('in_progress','completed') DEFAULT 'in_progress',
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (mission_id) REFERENCES Missions(mission_id)
);

-- Reviews 테이블
CREATE TABLE Reviews (
    review_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    assignment_id BIGINT NOT NULL,
    rating INT NULL,
    comment TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (assignment_id) REFERENCES Mission_Assignments(assignment_id)
);
