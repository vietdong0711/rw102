-- trước khi tạo account, nếu phòng ban đã có >= 2 thành viên thì thông báo lỗi "Phòng ban đã đầy"
-- insert acccount, department_id = 1

-- kiểm tra xem phòng ban có department_id= 1 đã có bao nhiêu thành viên 
select count(1)
from account acc
left join department dep on acc.department_id = dep.department_id
where dep.department_id =5;


	-- nếu >=2 báo lỗi
    -- nếu < 2   cho insert

-- dùng trigger: kiểm tra xem dữ liệu có hợp lệ 

-- function, procedure thì phải gọi ra mới dùng dc, 
-- trigger tự động gọi

-- trigger hoạt động khi nào?  trước khi tạo account   before  :    sau khi tạo account   after
-- hành động trong bài này là gì? thêm sửa xóa? tạo account   : insert
-- hành động này thực hiện với bảng nào? account

-- dữ liệu là mới(new) hay cũ(old)
-- với hành động thêm mới thì toàn bộ dữ liệu là new
-- update thì vừa có new, vừa có old
-- với hành động xóa thì dữ liệu là old hết 


delete from account where account_id IN (1,2,3,4);
-- VD: đi mua ip17prm màu cam vũ trụ:   ip17prm màu cam vũ trụ  new    	insert
-- VD: bán ip16prm đi;   ip16prm old									

-- UPDATE : đổi ip16prm lấy ip17prm và bù thêm tiền   ip16prm(old)   ip17prm(new)

update account set username = 'dongnv', fullname = 'nguyen viet dong' where account_id = 1;
					-- new 							-- new 							-- old

DELIMITER $$
CREATE trigger trigger1   
before insert on account  -- trước khi thêm dữ liệu vào bảng account  thì trigger sẽ hoạt độgn trước
for each row			-- khi thêm, sửa, xóa nhiều giá trị thì trigger sẽ dùng vòng lặp để check từng cặp giá trị 1 
BEGIN
	-- logic xử lý bài toán
	-- kiểm tra xem phòng ban có bao nhiêu thành viên
    declare sl int;
    select count(1) into sl
	from account acc
	left join department dep on acc.department_id = dep.department_id
	where dep.department_id = new.department_id;
    
    if sl >=2 then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'Phòng ban đã đầy';
    end if;
END$$
DELIMITER ;



insert into account(email, username, full_name, department_id, position_id)
	values 	('demo1@gmail.com', 'demo1', 'demo1', 5, 1),
			('demo1@gmail.com', 'demo1', 'demo1', 4, 1),
			('demo1@gmail.com', 'demo1', 'demo1', 5, 1),
			('demo1@gmail.com', 'demo1', 'demo1', 5, 1),
			('demo1@gmail.com', 'demo1', 'demo1', 5, 1);  -- thêm 5 account
    
-- khi run câu trên thì   trigger1  chạy trước    nếu có lỗi -> in ra thông báo
													-- ko có lỗi  -> insert bình thường
                                                    
-- trước khi xóa department thì kiểm tra xem có account nào đang liên quan ko
	-- nếu có thì báo lỗi 'ko thể xóa phòng này do phòng ban này đang có account!'
-- trước hay sau: before  
-- hành động là gì? delete
-- hành động ở bảng nào? department
-- dữ liệu là new hay old
-- delete from department where department_id = ?; -- 1
-- delete from department where department_name = ?;


DELIMITER $$
CREATE trigger trigger2  
before delete on department  -- trước khi xóa dữ liệu ở bảng department  thì trigger sẽ hoạt độgn trước
for each row			
BEGIN
	-- logic xử lý bài toán
	-- kiểm tra xem phòng ban có bao nhiêu thành viên
    declare sl int;
    select count(1) into sl
	from account acc
	left join department dep on acc.department_id = dep.department_id
	where dep.department_id = old.department_id;
    
    if sl > 0 then 
		signal sqlstate '88888'  -- 5 chữ số
        set message_text = 'ko thể xóa phòng này do phòng ban này đang có account!';
    end if;
END$$
DELIMITER ;

 delete from department where department_id = 6;
 
 select * from department 

-- bảng department có thêm cột số thành viên  (so_luong_thanh_vien)
-- sau khi thêm mới account thì tăng số lượng thành viên lên 1
-- sau khi xóa account thì sẽ giảm số lượng thành viên đi 1

-- sau khi thêm account thfi    tăng quantity-account ở bảng department lên 1(update): logic xử lý của trigger
-- 1 thêm account xong  2(trigger)
	-- 3 tăng quantity-account ở bảng department

DELIMITER $$
CREATE trigger trigger2  
after insert on account 
for each row			
BEGIN
	-- logic xử lý bài toán
    -- tìm ra số lượng ban đầu
    declare sl int;
    select quantity_account into sl
    from department where department_id = new.department_id;
    -- tăng quantity-account ở bảng department lên 1(update)
    
    update account set quantity_account = (sl+1) where department_id = new.department_id;
END$$
DELIMITER ;




