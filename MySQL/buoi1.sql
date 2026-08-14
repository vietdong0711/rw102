DROP DATABASE IF EXISTS rw_102;
CREATE DATABASE rw_102;

USE rw_102;-- su dung rw_102

-- Table 1:Department  
-- DepartmentID:  định danh của phòng ban (auto increment) 
-- DepartmentName: tên đầy đủ của phòng ban (VD: sale, marketing, …) 
-- Datatype: kiểu dữ liệu
	-- kí tự : varchar(50),    độ dài tối đa là 50 kí tự
    -- number: số nguyên  	int, ...
			-- số thực		float, double
    -- ngày tháng:   yyyy-MM-dd(2026-08-12)  - date
			-- 	 20:39:00				- time
            -- 2026-08-12 20:39:00		- datetime
	-- enum: kí tự   - đặc biệt - tự cấu hình 1 vài option, và dữ liệu chỉ chọn	
									-- 1 trong các option trên
DROP TABLE IF EXISTS department;
CREATE TABLE department (
	department_id 		INT UNSIGNED,
	department_name		VARCHAR(50)
);
-- viết hoa : bôi đen   +   ctrl shift U

-- 0333444555  varchar  int   --   01 : 1  +84

-- user:   id, full_name, username, birth_of_date, gender
CREATE TABLE user(
	id  			INT, 
    full_name 		VARCHAR(50),
    username 		VARCHAR(50),
    birth_of_date 	DATE,
    gender 			ENUM('MALE', 'FEMALE')
);

-- xem dữ liệu trong bảng
--   select * from + tên bảng  ;
SELECT *
FROM department;

SELECT *
FROM user;

-- thêm dữ liệu
--   INSERT INTO + tên bảng(tên các cột muốn thêm dữ liệu)
--  	VALUES  (các cặp giá trị muốn thêm dữ liệu)
INSERT INTO department(department_id, department_name)
	VALUES 	(1, 'Sale'),
			(2, 'Marketing'),
            (3, 'Bảo vệ');
            
INSERT INTO user(id, full_name, username, birth_of_date, gender)
		VALUES 	(1, 'nguyen van an', 'annv', '2000-01-01', 'MALE');

    





