-- dữ liệu nằm ở bảng nào?
-- yêu cầu đề bài là gi?
-- các bước thực hiện ntn?

-- với các bài toán thống kê, tìm nhỏ nhất  thì dùng left hoặc right join, group theo bên đủ gtri, đếm theo bên có gtri null
-- với các bài toán tìm lớn nhất, đếm > n, tìm dữ liệu chung thì dùng inner join

-- Exercise 1: Join 
-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ 
-- dữ liệu nằm ở bảng nào?		account  + department
-- yêu cầu đề bài là gi? 		lấy ra thông tin account + tên phòng ban
-- các bước thực hiện ntn?		left join 

select * 
from account acc
left join department dep on acc.department_id = dep.department_id;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010  
-- dữ liệu nằm ở bảng nào?   	account,  tên phòng ban, chức vụ
-- yêu cầu đề bài là gi?		sau ngày 20/12/2010  
-- các bước thực hiện ntn?		left JOIN
select * 
from account acc
left join department dep on acc.department_id = dep.department_id
left join position pos on pos.position_id = acc.position_id
where acc.created_date > '2010-12-20';

-- Question 3: Viết lệnh để lấy ra tất cả các developer(người lập trình)  (develop = DEV), 
-- dữ liệu nằm ở bảng nào?	account + position
-- yêu cầu đề bài là gi?	position_name = dev
-- các bước thực hiện ntn?	join
select * 
from account acc
join position pos on pos.position_id = acc.position_id  -- ko lấy cac account ko cho chức vụ: 9
where pos.position_name like 'DEV';

select * 
from account acc
left join position pos on pos.position_id = acc.position_id -- lấy cac account ko cho chức vụ: 12
where pos.position_name like 'DEV';

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên 
-- dữ liệu nằm ở bảng nào?		department  + account
-- yêu cầu đề bài là gi?		tìm các phòng ban > 3 nv
-- các bước thực hiện ntn?		join   + group by + đếm
select *  -- dep.*, count(1)
from department dep
inner join account acc on acc.department_id = dep.department_id
group by dep.department_id
having count(1) > 2;

-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất 
-- dữ liệu nằm ở bảng nào? 		question + exam_question 
-- yêu cầu đề bài là gi?		ds câu hỏi dc dùng nhiều nhất
-- các bước thực hiện ntn?		join + group by + đếm
select count(1)  -- 3
from question q
join exam_question eq on q.question_id = eq.question_id
group by eq.question_id
order by count(1) desc 
limit 1;

-- tìm các câu hỏi dc sử dụng 3 lần trrong đề thi
select * -- q.*, count(1)  -- 3
from question q
join exam_question eq on q.question_id = eq.question_id
group by eq.question_id
having count(1) = (select count(1)  -- 3
					from question q
					join exam_question eq on q.question_id = eq.question_id
					group by eq.question_id
					order by count(1) desc 
					limit 1);

-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question 
-- dữ liệu nằm ở bảng nào?		question   + category_question
-- yêu cầu đề bài là gi?		đếm xem mỗi category dc dùng trong bao nhiêu câu hỏi
-- các bước thực hiện ntn?		join, group by  , đếm
select cq.category_id,  count(q.category_id)
from question cq  -- đủ
right join category_question q on cq.category_id = q.category_id -- thiếu
group by cq.category_id;
-- sẽ phải hiện thị toàn bộ category  -> category_Question đủ



-- các bài toán liên quan đến thông kê thì số lượng là 0 cũng phải thống
-- khi dùng inner join thì group theo cột nào, đếm theo cột nào cx dc
-- khi dùng left hoặc right join thì phải group theo bên cột đủ giá trị và đếm theo bên thiếu giá trị(count(null) = 0)


-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam
-- dữ liệu nằm ở bảng nào? 	question + exam_question
-- yêu cầu đề bài là gi?	thống kê
-- các bước thực hiện ntn?	left join , group theo bên question, đến theo bên exam_question

 
-- Question 8: Lấy ra Question có nhiều câu trả lời nhất 
-- dữ liệu nằm ở bảng nào?		question	answer
-- yêu cầu đề bài là gi?		tìm câu hỏi có nhiều câu tra lời nhất
-- các bước thực hiện ntn?		join 
-- b1: tìm lớn nhát
select q.*, count(1)
from question q
join answer a on q.question_id = a.question_id
group by q.question_id
order by count(1) desc 
limit 1;
-- b2: tìm các question co số câu trả lời = max




-- Question 9: Thống kê số lượng account trong mỗi group  ==  thống kê mỗi group có bao nhiêu account
-- dữ liệu nằm ở bảng nào?		group + group_account
-- yêu cầu đề bài là gi?		thồng kê số lượng account trong mỗi group
-- các bước thực hiện ntn?		left join , group by theo group, đếm theo group_account




-- Question 10: Tìm chức vụ có ít người nhất  
-- dữ liệu nằm ở bảng nào?  	position + account
-- yêu cầu đề bài là gi?
-- các bước thực hiện ntn?		left join(để lấy các phòng ban chauw có account nào)
					--				-- group by theo bên position, đếm theoi account

select * from account;
-- b1: tìm số người ít nhất trong 1 chức vụ = min
select count(acc.position_id )
from position po
left join account acc on po.position_id = acc.position_id 
group by po.position_id 
order by count(acc.position_id ) asc
limit 1;
-- b2: tìm các chức vụ có số nuguwowfgi = min
select po.*,  count(acc.position_id)
from position po
left join account acc on po.position_id = acc.position_id 
group by po.position_id 
having count(acc.position_id) = (select count(acc.position_id )
									from position po
									left join account acc on po.position_id = acc.position_id 
									group by po.position_id 
									order by count(acc.position_id ) asc
									limit 1);

-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM   
-- dữ liệu nằm ở bảng nào?		department      	account			position
-- yêu cầu đề bài là gi?		thống kê mỗi phfogn ban có bao nhieuie dev, test, SC, PM
-- các bước thực hiện ntn?`		left join  group by đếm
select de.*, po.*, count(po.position_id)
from department de
left join account acc on de.department_id= acc.department_id
left join position po on acc.position_id = po.position_id
group by de.department_id, po.position_id;


SELECT d.department_id,d.department_name,
    COUNT(CASE WHEN p.position_name = 'DEV' THEN 1 END) AS DEV,
    COUNT(CASE WHEN p.position_name = 'TEST' THEN 1 END) AS TEST,
    COUNT(CASE WHEN p.position_name = 'SCRUM_MASTER' THEN 1 END) AS SCRUM_MASTER,
    COUNT(CASE WHEN p.position_name = 'PM' THEN 1 END) AS PM
FROM department d
LEFT JOIN `account` a ON d.department_id = a.department_id
LEFT JOIN `position` p ON a.position_id = p.position_id
GROUP BY d.department_id;

SELECT dep.department_name,
    (SELECT COUNT(*)
     FROM account acc 
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'Dev') AS so_dev,
    (SELECT COUNT(*)
     FROM account acc 
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'Test') AS so_test,
    (SELECT COUNT(*)
     FROM account acc
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'Scrum Master') AS so_scrum_master,
    (SELECT COUNT(*)
     FROM account acc 
     JOIN position pos ON acc.position_id = pos.position_id
     WHERE acc.department_id = dep.department_id AND pos.position_name = 'PM') AS so_pm
FROM department dep;

SELECT
    d.department_id,
    d.department_name,
    p.position_name,
    COUNT(a.account_id) AS total_members
FROM department d
CROSS JOIN position p
LEFT JOIN account a ON d.department_id = a.department_id AND p.position_id = a.position_id
GROUP BY d.department_id, d.department_name, p.position_name, p.position_id
ORDER BY d.department_id, p.position_id;

-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: 
-- thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, … 
-- dữ liệu nằm ở bảng nào?		question, type_question, account, answer
-- yêu cầu đề bài là gi?			Lấy thông tin chi tiết của câu hỏi bao gồm
-- các bước thực hiện ntn?			left join



-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm 
			-- Thống kê xem có bao nhiêu câu tự luận, trawcsc nghiệm
-- dữ liệu nằm ở bảng nào?		question, type_question
-- yêu cầu đề bài là gi?		thống kê
-- các bước thực hiện ntn?		right join, group by bên đủ , đếm bên thiếu



-- Question 14:Lấy ra group không có account nào 
-- dữ liệu nằm ở bảng nào?    group+  group_account
-- yêu cầu đề bài là gi?		Lấy ra group không có account nào 
-- các bước thực hiện ntn?		left join + group by + having count = 0
SELECT g.*
FROM `Group` g
LEFT JOIN Group_Account ga ON g.Group_ID = ga.Group_ID
WHERE ga.Account_ID IS NULL;
 
-- Question 16: Lấy ra question không có answer nào 
	-- tương tự bài 14

-- UNION: chú ý các bảng cùng số lượng cột
-- Question 17:  
-- Lấy các account thuộc nhóm thứ 1 
-- Lấy các account thuộc nhóm thứ 2 
-- Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau 
--  
-- Question 18:  
-- Lấy các group có lớn hơn 5 thành viên 
-- Lấy các group có nhỏ hơn 7 thành viên 
-- Ghép 2 kết quả từ câu a) và câu b) 
