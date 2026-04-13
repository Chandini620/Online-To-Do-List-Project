
CREATE DATABASE todo_db;

USE todo_db;

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    task VARCHAR(255),
    status VARCHAR(20)
);
