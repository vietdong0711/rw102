-- dữ liệu nằm ở bảng nào? 
-- điều kiện đề bài yêu cầu là gì? 

-- Question 2: Lấy ra tất cả các phòng ban 
select *
from department;

-- Question 3: Lấy ra id của phòng ban "Sale" 
select department_id
from department
where department_name like 'Marketing';



-- Question 4: Lấy ra thông tin account có full name dài nhất 
-- dữ liệu nằm ở bảng nào? account
-- điều kiện đề bài yêu cầu là gì? full name dài nhất - có nhiều kí tự nhất
-- các bước làm: sắp xếp độ dài fullname giảm dần    limit 1

select *, length(full_name) as 'độ dài'
from account
order by length(full_name) desc
limit 1;


select *, char_length(full_name) as 'độ dài'
from account
order by length(full_name) desc
limit 1;

select  char_length('Nguyễn Viết Đồng'); -- 16
select  length('Nguyễn Viết Đồng');  -- Nguyeenx Vieets DDoofng
select length('Nguyeenx Vieets DDoofng');

-- b1 tìm ra độ dài dài nhất của fullname   max  
select length(full_name)
from account
order by length(full_name) desc
limit 1;   -- 17

select max(length(full_name))
from account;

-- b2 tìm ra các tên có độ dài = max
select *, length(full_name) as 'độ dài'
from account
where length(full_name) = 17;



select *, length(full_name) as 'độ dài'
from account
where length(full_name) = (select length(full_name)
							from account
							order by length(full_name) desc
							limit 1); -- subquey: câu select lồng trong câu sql khác



-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id  = 3 
-- b1 tìm ra độ dài dài nhất của fullname thuộc phòng ban có id = 3  max  
select length(full_name)
from account
where department_id = 3
order by length(full_name) desc
limit 1;   -- 17

select max(length(full_name))
from account
where department_id = 3;


-- b2 tìm ra các tên có độ dài = max thuộc phong ban có id = 3
select *, length(full_name) as 'độ dài'
from account
where department_id = 3 and length(full_name) = (select max(length(full_name))
													from account
													where department_id = 3);

select *, length(full_name) as 'độ dài'
from account
where length(full_name) = (select max(length(full_name))
													from account
													where department_id = 3); -- sai

-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019 
-- lấy ra các group có ngày tạo trước 20/12/2019 

select *
from group_table
where created_date < '2019-12-20';

-- Question 7: Lấy ra ID của question có >= 4 câu trả lời 
-- dữ liệu nằm ở bảng nào?  question_id và liên quan đến câu trả lời    answer
-- điều kiện đề bài yêu cầu là gì? >= 4 câu trả lời 
-- cách làm ntn? nhóm theo question_id    đếm >=4

SELECT question_id
FROM answer
group by question_id
having count(1) >= 3;

-- Lấy ra ID của question có nhiều câu trả lời nhất
-- b1: tìm ra số lượng câu trả lời nhiều nhất của 1 câu hỏi là bao nhiêu  = max
-- b2: tìm ra các câu hỏi có số câu trả lời = max
SELECT count(1)
FROM answer
group by question_id
order by count(1) desc
limit 1; -- 3

SELECT question_id
FROM answer
group by question_id
having count(1) = 3;

SELECT question_id
FROM answer
group by question_id
having count(1) = (SELECT count(1)
					FROM answer
					group by question_id
					order by count(1) desc
					limit 1);

-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày 20/12/2019 
-- dữ liệu nằm ở bảng nào? exam
-- điều kiện đề bài yêu cầu là gì? thời gian thi >= 60 phút     tạo trước ngày 20/12/2019 
-- cách làm: 
select code
from exam
where duration >= 60 and created_date < '2019-12-20';

-- Question 9: Lấy ra 5 group được tạo gần đây nhất 
select * 
from `group`
order by created_date desc
limit 2;

-- Question 10: Đếm số nhân viên thuộc department có id = 2 
select count(1)
from account
where department_id = 2;

-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o" 
select *
from account
where full_name like 'D%' and full_name like '%o';

-- Nguyen Viet Dong      --- tên: Dong
select substring_index('Nguyen Viet Dong', ' ', -2);

select *
from account
where substring_index(full_name, ' ', -1) like 'D%o';

-- '% D%o';
nguyen duc no



-- Question 12: Xóa tất cả các exam được tạo trước ngày 20/12/2019  
delete from exam where created_date < '2019-12-20';

-- Question 13: Xóa tất cả các question có nội dung bắt đầu bằng từ "câu hỏi" 
delete from question where content like 'câu hỏi';

-- Question 14: Update thông tin của account có id = 5 thành tên "Nguyễn Bá Lộc" và email thành loc.nguyenba@vti.com.vn 
update account set full_name = 'Nguyễn Bá Lộc', email = 'loc.nguyenba@vti.com.vn' where account_id = 5;

-- Question 15: update account có id = 5 sẽ thuộc group có id = 4 
update group_account set group_id = 4 where account_id = 5;






