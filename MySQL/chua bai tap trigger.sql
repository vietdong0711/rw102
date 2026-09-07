-- trigger hoạt động trước hay sau 
-- hành động là gì? với bảng nào
-- dữ liệu là new hay old? 
	-- insert: new 
    -- delete : old
    -- update: trong SET new  , trong WHERE old

-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo trước 1 năm trước 
-- trigger hoạt động trước hay sau    	check trước khi tạo   -> before
-- hành động là gì? với bảng nào		insert on group
-- dữ liệu là new hay old? 				new
DELIMITER $$
CREATE trigger question1  
before insert on `group` 
for each row			
BEGIN
	-- logic xử lý bài toán
    -- check xem ngày tạo < 1 năm trước hay ko?
    if new.created_date < date_sub(now(), interval 1 year) then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'không thể tạo group này!';
    end if;
END$$
DELIMITER ;
 
select * from `group`;
insert into `group`(group_name, created_date, creator_id)
	values ('A', '2026-01-01', 1);
 
-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào department "Sale" nữa,
-- khi thêm thì hiện ra thông báo "Department "Sale" cannot add more user" 

-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		insert account
-- dữ liệu là new hay old? 				new 
DELIMITER $$
CREATE trigger question2  
before insert on account
for each row			
BEGIN
	-- logic xử lý bài toán
    -- tìm ra department name từ department id
    declare dep_name varchar(100);
    select department_name into dep_name
    from department
    where department_id = new.department_id;
    
    if dep_name = 'Sales' then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'Department "Sale" cannot add more user!';
    end if;
END$$
DELIMITER ;
drop trigger trigger1;

select * from department;
insert into account(email, username, full_name, department_id, position_id)
	values 	('demo12@gmail.com', 'demo12', 'demo12', 2, 1);

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user 
-- làm sao để biết dc group này có các account nào? group_account: group nào chứ những account nào

-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		insert group_account
-- dữ liệu là new hay old? 				new
DELIMITER $$
CREATE trigger question3  
before insert on group_account 
for each row			
BEGIN
	-- logic xử lý bài toán
    -- check xem group sắp thêm account nào đang có bao nhiêu account?
    declare sl int;
    select count(1) into sl
    from group_account
	where group_id = new.group_id;-- 3

    if sl >= 3 then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'không thể thêm account vào group này!';
    end if;
END$$
DELIMITER ;

insert into group_account(group_id, account_id, join_date)
	values (1, 4, now());

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question 
-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		insert  	exam_question
-- dữ liệu là new hay old? 				new 
-- tương tự bài số 3

-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là admin@gmail.com          
--  (đây là tài khoản admin, không cho phép user xóa), 
-- còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông tin liên quan tới user đó
-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		delete  	account
-- dữ liệu là new hay old? 				old
DELIMITER $$
CREATE trigger question5  
before delete on account
for each row			
BEGIN
	-- logic xử lý bài toán
    -- check xem email có phải admin ko
    if old.email = 'admin@gmail.com' then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'đây là tài khoản admin, không cho phép user xóa!';
    end if;
    -- xóa các thông tin liên quan 
    -- delete group_account
    -- detete group
    -- exam_question
    -- exam
    -- question
END$$
DELIMITER ;

select * from account;
delete from account where account_id = 1;

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table Account,   -- default ''
-- hãy tạo trigger cho phép người dùng khi tạo account không điền vào departmentID
-- thì sẽ được phân vào phòng ban "waiting Department"  
 
-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		insert account
-- dữ liệu là new hay old? 				new
DELIMITER $$
CREATE trigger question7  
before insert on account
for each row			
BEGIN
	-- logic xử lý bài toán
    -- check xem ngày tạo < 1 năm trước hay ko?
    declare dep_id int;
    if new.department_id is null then 
		-- tìm department_id của phòng ban "waiting Department"
        -- chưa có phòng ban "waiting Department"   -> 
        
        select department_id into dep_id    -- null
        from department where department_name like 'waiting Department';
        if dep_id is null then
			insert into department(department_name) values ('waiting Department');
            select department_id into dep_id    -- null
			from department where department_name like 'waiting Department';
        end if;
        
        SET new.department_id = dep_id;  -- null
    end if;
END$$
DELIMITER ;

select * from department;
insert into account(email, username, full_name, position_id)
	values 	('demo12@gmail.com', 'demo12', 'demo12', 1);


-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi question,
-- trong đó có tối đa 2 đáp án đúng. 
-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		insert answer
-- dữ liệu là new hay old? 				new 
DELIMITER $$
CREATE trigger question7_1
before insert on answer
for each row			
BEGIN
	-- logic xử lý bài toán
	declare count_all int;
    declare count int;
    -- tìm ra số câu trả lời hiện tại
    select count(1) into count_all
    from answer where question_id = new.question_id;
    if count_all >= 4 then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'Không thể tạo câu trả lời cho câu hỏi này!';
	else 
		-- đém số câu trả lòi đúng trong câu hỏi đó
		select count(1) into count  
		from answer where question_id = new.question_id and is_correct = 0;
        if count >= 2 then 
			signal sqlstate '88888'  -- 5 chữ số
			set message_text = 'Không thể tạo câu trả lời cho câu hỏi này!';
        end if;
    end if;    
END$$
DELIMITER ;

select *
		from answer where question_id = 1;
        
insert into answer(content, question_id, is_correct)
	values ('demo1234?' , 1, '1');


-- Question 8: Viết trigger sửa lại dữ liệu cho đúng: Nếu người dùng nhập vào gender của account là nam, nữ,
-- chưa xác định thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database 
DELIMITER $$
CREATE trigger question8
before insert on account
for each row			
BEGIN
	-- logic xử lý bài toán
    if new.gender = 'nam' then 
		SET new.gender = 'M';
	end if;  
	if new.gender = 'nữ' then
		SET new.gender = 'F';
	end if;
    if new.gender = 'khác' then
		SET new.gender = 'U';
    end if;    
END$$
DELIMITER ;

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày 
-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		detele exam
-- dữ liệu là new hay old? 				 old

DELIMITER $$
CREATE trigger question9  
before delete on exam
for each row			
BEGIN
	-- logic xử lý bài toán
    -- check xem ngày tạo của đề thi đó cách đây 2 ngày ko
    if old.created_date > subdate(now(), interval 2 day) then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'không thể xóa bài thi mới tạo được 2 ngày!';
    end if;
    -- xóa các thông tin liên quan 
    -- exam_question
END$$
DELIMITER ;

select * from exam;

-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, 
-- delete các question khi question đó chưa nằm trong exam nào 
-- trigger hoạt động trước hay sau 		before
-- hành động là gì? với bảng nào		detele question
-- dữ liệu là new hay old? 				old

DELIMITER $$
CREATE trigger question10
before delete on question
for each row			
BEGIN
	-- logic xử lý bài toán
    -- cdem xem câu hỏi đó nằm trong bao nhiêu đề thi
    declare sl int;
    SET sl = 0;
    select count(1) into sl 
    from exam_question where question_id = old.question_id;
    if sl != 0 then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'không thể câu hỏi này vì cau hỏi này đang tồn tại trong đề thi!';
    end if;
    -- xóa các thông tin liên quan 
    -- answer
END$$
DELIMITER ;

select * from exam;







