DROP DATABASE IF EXISTS rw_102;
CREATE DATABASE rw_102;

USE rw_102;-- su dung rw_102

-- Table 1:Department  
-- DepartmentID:  định danh của phòng ban (auto increment) khóa chính
-- DepartmentName: tên đầy đủ của phòng ban (VD: sale, marketing, …) 

-- PRIMARY KEY: khóa chính: định danh cho từng row trong table đó, các row phân biệt với nhau thông qua khóa chính
-- AUTO_INCREMENT: khóa chính tự tăng, khi tạo dữ liệu thì ko cần thêm khóa chính vào
DROP TABLE IF EXISTS department;
CREATE TABLE department (
	department_id 		INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, 
	department_name		VARCHAR(50)
);
SELECT * FROM department;
INSERT INTO department(department_name)
	VALUES 	('Marketing'),
			('Sale'),
			('Bảo vệ'),
			('Nhân sự'),
			('Kỹ thuật'),
			('Tài chính'),
			('Phó giám đốc'),
			('Giám đốc'),
			('Thư ký'),
			('Chờ việc');
            
-- Table 2: Position  
-- PositionID:  định danh của chức vụ (auto increment) 
-- PositionName: tên chức vụ (Dev, Test, Scrum Master, PM) 
DROP TABLE IF EXISTS position;
CREATE TABLE position (
	position_id 		INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, 
	position_name		ENUM('DEV', 'TEST', 'SCRUM_MASTER', 'PM')
);
SELECT * FROM position;
DROP TABLE IF EXISTS account;
CREATE TABLE account (
	account_id 		INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, 
	email			varchar(100) UNIQUE NOT NULL,
    username		varchar(100) UNIQUE NOT NULL,
    full_name		varchar(100) DEFAULT 'NO NAME', -- không nhập fullname thì sẽ để giá trị mặc định là "NO NAME"
    department_id	INT UNSIGNED,
    position_id		INT UNSIGNED,
    create_date		datetime DEFAULT CURRENT_TIMESTAMP, -- ko nhập ngày tạo thfi sẽ lấy ngày hiện tại làm ngày tạo
    foreign key (department_id) references department(department_id),
    constraint fk_acc_pos foreign key (position_id) references `position`(position_id),
    constraint check_username check (length(username) > 8)
);
--  constraint + tên ràng buộc + foreign key + cột khóa ngoại + references + tên bảng liên quan (cột liên quan)
-- UNIQUE: không chứa dc các giá trị trùng nhau
-- UNIQUE  và Primary key khác nhau ntn?   UNIQUE chứa dc null, Primary key ko chứa dc null
-- DEFAULT: khi ko truyền gtri cho cột đó thì sẽ lấy gtri ở default làm gtri
-- NOT NULL: cột đó phải có gtri
-- CHECK: kiểm tra giá trị có hợp lệ không
INSERT INTO account (email, username, department_id, position_id)
	VALUES 	('an.nguyen@vti.com.vn', 'an', 1, 1);

INSERT INTO account (email, username, full_name, department_id, position_id)
	VALUES 	('an.nguyen@vti.com.vn', 'annguyen', 'Nguyễn Văn An', 1, 1),
			('binh.tran@vti.com.vn', 'binhtran', 'Trần Bình', 2, 2),
			('cuong.le@vti.com.vn', 'cuongle', 'Lê Văn Cường', 3, 1),
			('dungdo@vti.com.vn', 'dungdo', 'Dung Do', 2, 3),
			('hoa.pham@vti.com.vn', 'hoapham', 'Phạm Thị Hoa', 4, 4),
			('khanh.vo@vti.com.vn', 'khanhvo', 'Võ Hoàng Khánh', 5, 1),
			('linh.ngo@vti.com.vn', 'linhngo', 'Ngô Thùy Linh', 6, 2),
			('minh.bui@vti.com.vn', 'minhbui', 'Bùi Quang Minh', 7, 3),
			('nam.phan@vti.com.vn', 'namphan', 'Phan Hoài Nam', 8, 4),
			('dao@vti.com.vn', 'daoo', 'Dao', 2, 1);
            
INSERT INTO account (username, full_name, department_id, position_id)
	VALUES 	('abc', 'Nguyễn Văn Aanhmdkalsdnsa', 2, 2);
INSERT INTO account (account_id,username, full_name, department_id, position_id)
	VALUES 	(null,'abc1', 'Nguyễn Văn Aanhmdkalsdnsa', 2, 2);


select * from account;



-- tối ưu lại các bảng (khóa chính, khóa ngoại, các ràng buộc)
-- insert dữ liệu

-- create 11 table 

-- insert into cho 11










