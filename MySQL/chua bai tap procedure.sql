-- b1: viết dc phần xử lý logic
-- b2: xác định tham só đàu vào và lắp vào câu tạo procedure


-- Question 1: Tạo store để người dùng nhập vào tên phòng ban 
-- và in ra tất cả các account thuộc phòng ban đó 

	-- in ra tất cả account thuộc phong ban Sales
select *
from account acc
join department dep on acc.department_id = dep.department_id
where dep.department_name like 'Sales';
-- tham số: tên phòng ban 


DELIMITER $$
CREATE PROCEDURE question1 (IN v_dep_name VARCHAR(100))
BEGIN
	-- logic xử lý bài toán
	select *
	from account acc
	join department dep on acc.department_id = dep.department_id
	where dep.department_name like v_dep_name;
END$$
DELIMITER ;

CALL question1('Marketing');
-- Question 2: Tạo store để in ra số lượng account trong mỗi group  
-- in ra số lượng account trong mỗi group  
-- group + group_account
select g.*, count(ga.group_id)
from `group` g 
left join group_account ga on g.group_id = ga.group_id
group by g.group_id;

select *
from `group` g 
left join group_account ga on g.group_id = ga.group_id
-- group by ga.group_id;
-- nhóm theo g.group_id  và đếm theo bên g.group_id

-- nhóm theo g.group_id  và đếm theo bên ga.group_id

-- nhóm theo ga.group_id  và đếm theo bên g.group_id  
	-- 5 nhóm   1 2 3 4 null  đếm theo bên  g.group_id  = 2
-- nhóm theo ga.group_id  và đếm theo bên ga.group_id  = 0
DELIMITER $$
CREATE PROCEDURE question2 ()
BEGIN
	-- logic xử lý bài toán
	select g.*, count(ga.group_id)
	from `group` g 
	left join group_account ga on g.group_id = ga.group_id
	group by g.group_id;
END$$
DELIMITER ;
CALL question2();
-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại 
DELIMITER $$
CREATE PROCEDURE question3 ()
BEGIN
	-- logic xử lý bài toán
	select tq.*, count(q.type_id)
	from type_question tq
	left join question q on tq.type_id = q.type_id and month(q.created_date) = month(now()) and year(q.created_date) = year(now())
	group by tq.type_id;
END$$
DELIMITER ;
CALL question3();

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất

DROP  PROCEDURE question4;
DELIMITER $$
CREATE PROCEDURE question4 ()
BEGIN
	-- logic xử lý bài toán
    with cte_q4 as (
		select tq.*, count(1) as sl 
        from type_question tq 
        join question q on tq.type_id = q.type_id
        group by tq.type_id
    )
	select type_id, type_name
    from cte_q4
    where sl = (select max(sl) from cte_q4);
    
END$$
DELIMITER ;

CALL question4();

-- TH: chỉ có 1 max thì gán 1 out
-- TH: có 2gtri = max phải gán vào 2 out 


-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question 


-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên chứa chuỗi 
-- của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào 
select group_name
from `group` where group_name like '%b%'
UNION ALL 
select username
from account where username like '%b%'

DELIMITER $$
CREATE PROCEDURE question6 (IN v_name VARCHAR(100))
BEGIN
	-- logic xử lý bài toán
    select group_name
	from `group` where group_name like concat('%',v_name,'%')
	UNION ALL 
	select username
	from account where username like concat('%',v_name,'%');
END$$
DELIMITER ;

CALL question6('bi');
-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:  
--  	username sẽ giống email nhưng bỏ phần @..mail đi  	
	-- abc@gmail.com   -> abc
-- positionID: sẽ có default là developer 
-- 	 	departmentID: sẽ được cho vào 1 phòng chờ 
--  Sau đó in ra kết quả tạo thành công 
DELIMITER $$
CREATE PROCEDURE question7 (IN v_full_name VARCHAR(100), IN v_email VARCHAR(100))
BEGIN
	-- logic xử lý bài toán
    declare v_username varchar(100);  -- variable
	declare dep_id int;  -- variable
	declare pos_id int;  -- variable
    
    select substring_index(v_email ,'@', 1) into v_username;
    
    -- tìm id của phong ban sale, id của chức vụ dev 
    -- neu chưa có phòng chờ thì tạo phòng chờ
    select department_id into dep_id -- gán gtri cho dep_id
    from department where department_name like '%Phòng chờ%';
    -- nếu chưa tồn tại phòng chờ thì tạo
    if dep_id is null then 
		insert into department(department_name) values ('Phòng chờ');
        
        select department_id into dep_id -- gán gtri cho dep_id
		from department where department_name like '%Phòng chờ%';
    end if;
    -- nếu chưa tồn tại thì thêm mới
    --  insert into department(department_name) -- câu lệnh insert
	-- 	select distinct 'Phòng chờ1234'  
	-- 	from department 
	-- 	where not exists (select 1 from department where department_name like 'Phòng chờ1234');
    
    select position_id into pos_id   -- gán gtri cho pos_id
    from position where position_name like 'DEV';

    insert into account(email, username, full_name, department_id, position_id)
		values (v_email, v_username, v_full_name, dep_id, pos_id);
        
	select 'tạo thành công';
END$$
DELIMITER ;

insert into department(department_name) -- câu lệnh insert
select distinct 'Phòng chờ1234'  
from department 
where not exists (select 1 from department where department_name like 'Phòng chờ1234');
-- kiểm tra phòng ban có tên Phòng chờ1234 tồn tại chưa
-- nếu chưa tồn tại thì trả ra Phòng chờ1234, nếu tồn tại rồi thì ko trả ra gì

CALL question7('demo54321', 'demo54321@gmail.com');

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay 
-- hoặc Multiple-Choice để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất 
DELIMITER $$
CREATE PROCEDURE question8 (IN v_name ENUM('Essay', 'Multiple Choice') )
BEGIN
	-- logic xử lý bài toán
	with cte_question8 as (
		select q.*, tq.type_name 
        from type_question tq 
        join question q on tq.type_id = q.type_id and tq.type_name = v_name
    )
    select * 
    from cte_question8 
    where char_length(content) = (select char_length(content)
									from cte_question8
                                    order by char_length(content) desc 
                                    limit 1);
END$$
DELIMITER ;

CALL question8('Essay');

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID 
DELIMITER $$
CREATE PROCEDURE question9 (IN exam_id_delete INT)
BEGIN
	-- logic xử lý bài toán
    -- trước khi xóa exam thì phải xóa các bảng liên quan 
    delete from exam_question where exam_id = exam_id_delete;
    -- sau đó mới xóa exam
	delete from exam where exam_id = exam_id_delete;
END$$
DELIMITER ;

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử dụng store ở câu 9 để xóa) 
--           Sau đó in số lượng record đã remove từ các table liên quan trong khi removing 
DELIMITER $$
CREATE PROCEDURE question10 ()
BEGIN
	-- logic xử lý bài toán
    DECLARE done INT DEFAULT FALSE;
    declare v_exam_id_delete int;
    declare v_row_exam_delete int;
    declare v_row_exam_question_delete int;
    -- tìm ra các exam dc tạo từ 3 năm trước
    -- các id tìm ra ở dưới sẽ dc cho vào ds exam_cursor
    declare exam_cursor CURSOR FOR 
    select exam_id
    from exam 
    where created_date < date_sub(now(), interval 3 year);
    declare continue handler for not found set done = true;
    
    select count(1) into v_row_exam_delete  -- số row exam bị xóa
    from exam 
    where created_date < date_sub(now(), interval 3 year);
    
	select count(1) into v_row_exam_question_delete -- số row exam_question bị xóa
    from exam e
    join exam_question eq on e.exam_id = eq.exam_id
    where e.created_date < date_sub(now(), interval 3 year);

    OPEN exam_cursor;
    read_loop: LOOP
		FETCH exam_cursor into v_exam_id_delete;
        if done then
			leave read_loop;
        end if;
        -- gọi đến câu 9 để xóa exam theo id
        CALL question9(v_exam_id_delete);
    END LOOP;
    CLOSE exam_cursor;
    
    select concat('Số row exam bị xóa là: ', v_row_exam_delete);
    select concat('Số row exam_question bị xóa là: ', v_row_exam_question_delete);
END$$
DELIMITER ;

CALL question10 ();


-- Error Code: 1064. You have an error in your SQL syntax; 
-- check the manual that corresponds to your MySQL server version for the right 
-- syntax to use near 'declare continue handler for not found set done = true;     OPEN exam_cursor;   ' 
-- at line 22


-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng nhập 
-- vào tên phòng ban và các account thuộc phòng ban đó sẽ được chuyển về phòng ban default 
-- là phòng ban chờ việc .
-- logic update 
-- trước khi xóa phòng ban thì update các account liên quan ssang phòng ban chờ việc
-- tạo phòng ban chờ việc nếu như chưa tồn tại


-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay

with cte_month as (
        SELECT 1 AS month UNION ALL
        SELECT 2 UNION ALL
        SELECT 3 UNION ALL
        SELECT 4 UNION ALL
        SELECT 5 UNION ALL
        SELECT 6 UNION ALL
        SELECT 7 UNION ALL
        SELECT 8 UNION ALL
        SELECT 9 UNION ALL
        SELECT 10 UNION ALL
        SELECT 11 UNION ALL
        SELECT 12
) 
select c.month, count(q.question_id) as so_luong
from cte_month c
left join question q on c.month = month(q.created_date) and year(created_date)= year(now()) 
group by c.month;

 


-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất  
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong  tháng") 
-- 6 tháng gần nhất tìm ntn
-- TH : 8 7 6 5 4 3
-- tháng hiện tại là tháng 3 : 3 2 1 - 12 11 10

WITH RECURSIVE last_6_months AS (
        SELECT 0 AS n
        UNION ALL
        SELECT n + 1 FROM last_6_months WHERE n < 5
    );
    
WITH last_6_months AS (
	select month(now()) as month, year(now()) as year
    UNION ALL 
    select month(date_sub(now(), interval 1 month)) as month, year(date_sub(now(), interval 1 month))
    UNION ALL 
    select month(date_sub(now(), interval 2 month)) as month, year(date_sub(now(), interval 2 month))
    UNION ALL 
    select month(date_sub(now(), interval 3 month)) as month, year(date_sub(now(), interval 3 month))
    UNION ALL 
    select month(date_sub(now(), interval 4 month)) as month, year(date_sub(now(), interval 4 month))
    UNION ALL 
    select month(date_sub(now(), interval 5 month)) as month, year(date_sub(now(), interval 5 month))
)  
select c.*, 
case  
when count(q.question_id) = 0 then 'không có câu hỏi nào trong  tháng' else count(q.question_id)
end as 'số lượng'
from last_6_months c
left join question q on c.month = month(q.created_date) and c.year = year(q.created_date) 
group by c.month, c.year;



