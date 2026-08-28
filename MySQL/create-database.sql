create database rw102;
use rw102;

CREATE TABLE department
(
    department_id   INT AUTO_INCREMENT PRIMARY KEY,  -- 2
    department_name VARCHAR(100) UNIQUE NOT NULL
);

-- Position table
CREATE TABLE position
(
    position_id   INT AUTO_INCREMENT PRIMARY KEY,
    position_name varchar(100) UNIQUE NOT NULL
);

-- Account table
CREATE TABLE account
(
    account_id    INT AUTO_INCREMENT PRIMARY KEY,
    email         VARCHAR(100) UNIQUE NOT NULL,
    username      VARCHAR(100) UNIQUE NOT NULL,
    full_name     VARCHAR(100)        NOT NULL,
    department_id INT,  
    position_id   INT,
    created_date  TIMESTAMP DEFAULT current_timestamp,

    -- Check mail
    CONSTRAINT check_email CHECK (
        REGEXP_LIKE(email, '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
        ),

    -- Foreign key
    CONSTRAINT fk_account_department
        FOREIGN KEY (department_id) REFERENCES department (department_id),
						-- 1				BẢNG LIÊN QUAN-- 2
    CONSTRAINT fk_account_position
        FOREIGN KEY (position_id) REFERENCES position (position_id)
);

-- Group table
CREATE TABLE group_table
(
    group_id     INT AUTO_INCREMENT PRIMARY KEY,
    group_name   VARCHAR(50) UNIQUE NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator_id   INT,

    -- Foreign key
    CONSTRAINT fk_group_creator
        FOREIGN KEY (creator_id) REFERENCES account (account_id)
);

-- Group account table
CREATE TABLE group_account
(
    group_id   INT,
    account_id INT,
    join_date  DATETIME,

    -- Primary key
    CONSTRAINT pk_group_account
        PRIMARY KEY (group_id, account_id),

    -- Foreign key
    CONSTRAINT fk_group_account_group
        FOREIGN KEY (group_id) REFERENCES group_table (group_id)
            ON DELETE CASCADE,
    CONSTRAINT fk_group_account_account
        FOREIGN KEY (account_id) REFERENCES account (account_id)
            ON DELETE CASCADE
);

-- Type question table
CREATE TABLE type_question
(
    type_id   INT AUTO_INCREMENT PRIMARY KEY,
    type_name varchar(100) UNIQUE NOT NULL
);

-- Category question
CREATE TABLE category_question
(
    category_id   INT AUTO_INCREMENT PRIMARY KEY,
    category_name varchar(100) UNIQUE NOT NULL
);

-- Question table
CREATE TABLE question
(
    question_id  INT AUTO_INCREMENT PRIMARY KEY,
    content      TEXT,
    category_id  INT ,
    type_id      INT ,
    creator_id   INT ,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Foreign key
    CONSTRAINT fk_question_category
        FOREIGN KEY (category_id) REFERENCES category_question (category_id),
    CONSTRAINT fk_question_type
        FOREIGN KEY (type_id) REFERENCES type_question (type_id),
    CONSTRAINT fk_question_account
        FOREIGN KEY (creator_id) REFERENCES account (account_id)
);

-- Answer table
CREATE TABLE answer
(
    answer_id   INT AUTO_INCREMENT PRIMARY KEY,
    content     TEXT,
    question_id INT NOT NULL,
    is_correct  BOOLEAN DEFAULT FALSE,

    -- Foreign key
    CONSTRAINT fk_answer_question
        FOREIGN KEY (question_id) REFERENCES question (question_id)
            ON DELETE CASCADE
);

-- Exam table
CREATE TABLE exam
(
    exam_id      INT AUTO_INCREMENT PRIMARY KEY,
    code         varchar(50) NOT NULL UNIQUE,
    title        varchar(50),
    category_id  INT,
    duration     INT,
    creator_id   INT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Foreign key
    CONSTRAINT fk_exam_category
        FOREIGN KEY (category_id) REFERENCES category_question (category_id),
    CONSTRAINT fk_exam_account
        FOREIGN KEY (creator_id) REFERENCES account (account_id)
);

-- Exam question table
CREATE TABLE exam_question
(
    exam_id     INT,
    question_id INT,

    -- Primary key
    CONSTRAINT pk_exam_question
        PRIMARY KEY (exam_id, question_id),

    -- Foreign key
    CONSTRAINT fk_exam_question_exam
        FOREIGN KEY (exam_id) REFERENCES exam (exam_id)
            ON DELETE CASCADE,
    CONSTRAINT fk_exam_question_question
        FOREIGN KEY (question_id) REFERENCES question (question_id)
            ON DELETE CASCADE
);


-- INSERT DATA
-- Department table
INSERT INTO department (department_name)
VALUES ('Sales'),
       ('Marketing'),
       ('HR'),
       ('Engineering');

-- Position table
INSERT INTO position (position_name)
VALUES ('Dev'),
       ('Test'),
       ('Scrum Master'),
       ('PM');

-- Account table
INSERT INTO account (email, username, full_name, department_id, position_id)
VALUES ('linh.nguyen@sparklab.vn', 'linhng', 'Nguyen Linh Chi', 4, 1),
       ('minh.tran@sparklab.vn', 'minhtran', 'Tran Quang Minh', 4, 2),
       ('ha.le@sparklab.vn', 'hale', 'Le Thu Ha', 2, 3),
       ('phuc.vo@sparklab.vn', 'phucvo', 'Vo Gia Phuc', 1, 4);

-- Group table
INSERT INTO group_table (group_name, creator_id)
VALUES ('Bug Smashers', 2),
       ('Code Crafters', 1),
       ('Sprint Masters', 3),
       ('Deal Closers', 4);

-- Group account table
INSERT INTO group_account (group_id, account_id, join_date)
VALUES (1, 1, '2026-03-01 09:00:00'),
       (1, 2, '2026-03-01 09:05:00'),
       (2, 1, '2026-03-02 10:00:00'),
       (2, 3, '2026-03-02 10:10:00'),
       (3, 2, '2026-03-03 14:00:00'),
       (3, 3, '2026-03-03 14:05:00'),
       (4, 4, '2026-03-04 08:30:00'),
       (4, 1, '2026-03-04 08:45:00');

-- Type question table
INSERT INTO type_question (type_name)
VALUES ('Essay'),
       ('Multiple Choice');

-- Category question table
INSERT INTO category_question (category_name)
VALUES ('Java'),
       ('SQL'),
       ('Postman'),
       ('Scrum');

-- Question table
INSERT INTO question (content, category_id, type_id, creator_id)
VALUES ('What does JVM stand for?', 1, 2, 1),
       ('Which SQL command is used to get data from a table?', 2, 2, 2),
       ('What is the main purpose of Postman in API testing?', 3, 1, 2),
       ('Who is responsible for removing blockers in Scrum?', 4, 2, 3);

-- Answer table
INSERT INTO answer (content, question_id, is_correct)
VALUES ('Java Virtual Machine', 1, TRUE),
       ('Java Visual Model', 1, FALSE),
       ('Join Virtual Method', 1, FALSE),

       ('SELECT', 2, TRUE),
       ('DELETE', 2, FALSE),
       ('DROP', 2, FALSE),

       ('It helps test and manage APIs quickly', 3, TRUE),

       ('Scrum Master', 4, TRUE),
       ('Intern', 4, FALSE),
       ('Designer', 4, FALSE);

-- Exam table
INSERT INTO exam (code, title, category_id, duration, creator_id)
VALUES ('JV101', 'Java Quick Quiz', 1, 30, 1),
       ('SQ201', 'SQL Starter Test', 2, 45, 2),
       ('PT301', 'Postman Practice', 3, 25, 2),
       ('SC401', 'Scrum Mini Check', 4, 20, 3);

-- Exam question table
INSERT INTO exam_question (exam_id, question_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);
