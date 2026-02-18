CREATE DATABASE IF NOT EXISTS appdb;
USE appdb;


CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    password VARCHAR(50) NOT NULL,
    age INT,
    gender VARCHAR(10)
);

CREATE TABLE trainers (
    trainer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100),
    contact_number VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE workout_plans (
    plan_id INT AUTO_INCREMENT PRIMARY KEY,
    trainer_id INT,
    plan_name VARCHAR(100) NOT NULL,
    duration_weeks INT,
    description TEXT,
    FOREIGN KEY (trainer_id) REFERENCES trainers(trainer_id) ON DELETE CASCADE
);

-- Create equipment table
CREATE TABLE equipment (
    equipment_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    quantity INT,
    maintenance_status VARCHAR(50)
);

CREATE TABLE memberships (
    membership_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT,
    plan_type VARCHAR(20),
    start_date DATE,
    end_date DATE,
    total_fee DOUBLE,
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE
);

CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    membership_id INT,
    amount_paid DOUBLE,
    payment_date DATE,
    payment_status VARCHAR(20),
    FOREIGN KEY (membership_id) REFERENCES memberships(membership_id) ON DELETE CASCADE
);

INSERT INTO members (name, email, phone_number, password, age, gender) VALUES
('John Doe', 'john@example.com', '1234567890', 'password123', 25, 'Male'),
('Jane Smith', 'jane@example.com', '0987654321', 'password456', 30, 'Female');

INSERT INTO trainers (name, specialization, contact_number, email) VALUES
('Mike Johnson', 'Weight Training', '5551234567', 'mike@example.com'),
('Sarah Williams', 'Yoga', '5557654321', 'sarah@example.com');

INSERT INTO workout_plans (trainer_id, plan_name, duration_weeks, description) VALUES
(1, 'Beginner Weight Training', 8, 'Basic weight training program for beginners'),
(2, 'Advanced Yoga', 12, 'Advanced yoga poses and techniques');

INSERT INTO equipment (name, type, quantity, maintenance_status) VALUES
('Treadmill', 'Cardio', 5, 'Good'),
('Dumbbells', 'Strength', 20, 'Good'),
('Bench Press', 'Strength', 3, 'Repair');

INSERT INTO memberships (member_id, plan_type, start_date, end_date, total_fee) VALUES
(1, 'Monthly', '2024-01-01', '2024-01-31', 50.00),
(2, 'Yearly', '2024-01-01', '2024-12-31', 500.00);

INSERT INTO payments (membership_id, amount_paid, payment_date, payment_status) VALUES
(1, 50.00, '2024-01-01', 'Completed'),
(2, 500.00, '2024-01-01', 'Completed');