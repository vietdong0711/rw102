
-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
-- create view + tên + as
-- câu sql tương ứng

-- danh sách nhân viên thuộc phòng ban sale
create view question1 as
select acc.*, dep.department_name
from account acc
join department dep on acc.department_id = dep.department_id
where dep.department_name like 'Sales';

select * from question1;


CREATE VIEW Ds_Nv_Sale AS

SELECT acc.*, de.department_name
FROM account acc
left join department de ON dep.department_id = acc.Department_id
left join position po ON acc.position_id = po.position_id
where de.Department_name = 'Sale';

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất

-- dữ liệu nằm ở bảng nào?	acocount group_account
-- yêu cầu đề bài là gi?	account tham gia vào nhiều group nhất
-- các bước thực hiện ntn?	join group by    đếm   lắp câu lệnh tạo view

select count(acc.account_id)
from account acc
join group_account ga on acc.account_id = ga.account_id
group by acc.account_id
order by count(acc.account_id) desc
limit 1;  -- = 3
-- c1
create view question2 as
select acc.*, count(acc.account_id)
from account acc
join group_account ga on acc.account_id = ga.account_id
group by acc.account_id
having count(acc.account_id) = (select count(acc.account_id)
									from account acc
									join group_account ga on acc.account_id = ga.account_id
									group by acc.account_id
									order by count(acc.account_id) desc
									limit 1);
select * from     question2;                                
                                    
create view question2_2 as
with cte_q2 as (
	select acc.*, count(acc.account_id) as so_luong
	from account acc
	join group_account ga on acc.account_id = ga.account_id
	group by acc.account_id
)
select *
from cte_q2
where so_luong = (select max(so_luong)
					from cte_q2);
                    
select * from question2_2; 

-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) 
-- và xóa nó đi
-- dữ liệu nằm ở bảng nào?  	question
-- yêu cầu đề bài là gi?		tìm cac câu hỏi có content quá dài
-- các bước thực hiện ntn?		đếm xem content có bao nhiêu TỪ

select *
from question
where length(content) > 30; -- length: đếm số kí tự có tính dấu  Đồng =  ddoongf   7

select *
from question
where char_length(content) > 30;-- char_length: đếm số kí tự, ko tính dấu 	Đồng	4
select char_length('Nguyễn Viết Đồng');
-- làm sao để đếm dc số từ?   'Nguyễn Viết Đồng'    3
-- 'Nguyễn Viết Đồng'  		có dấu 2 " "    -> số từ =   3
-- 'Trần Thị Ngọc Châu'		có dấu 3 " "	-> số từ = 4 
--  số từ = số dấu " "  + 1
-- các TH phát sinh   dấu cách ở đầu và cuối trim()  ,   
-- thừa dấu cách, tab  ở giữa regexp_replace('chuõi', '[[:space:]]+', ' ')
	-- ' ' 1space, '        '  8space,   '				'  4tab = 16 space
    -- [[:space:]]+   n dấu space
    -- chung quy là các khoảng trắng   dc tạo nên từ các dấu space

create view question3 as
select *
from question
where char_length(trim(regexp_replace(content, '[[:space:]]+', ' ')))  -- đưa về 1 dấu cách giữa cac từ
	-  char_length(replace(trim(content), ' ', '')) + 1 > 8;
    
delete from question3 where true;

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
-- tương tự bài 2
-- view + cte
select d.*,count(1)
from department d
left join account acc on acc.department_id = d.department_id
group by d.department_id
having count(1) = (select  count(1)
					from department d
					left join account acc on acc.department_id = d.department_id
					group by d.department_id
                    order by count(1) desc
                    limit 1);
                    
create view question4 as
with cte_q4 as (
	select d.*,count(1) as sl
	from department d
	left join account acc on acc.department_id = d.department_id
	group by d.department_id	
)
select *
from cte_q4
where sl = (select max(sl) 
			from cte_q4);


-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo
-- dữ liệu nằm ở bảng nào?	question 	account  (department_id)
-- yêu cầu đề bài là gi?	
-- các bước thực hiện ntn?	join

create view question5 as
select q.*, acc.full_name
from question q
join account  acc on acc.account_id = q.creator_id
where acc.full_name like 'Nguyen %' or acc.full_name like 'Nguyễn %';


