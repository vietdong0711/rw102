import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Sale";

        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Marketing";

        Department department3 = new Department();
        department3.id = 3;
        department3.name = "Bảo vệ";

        Position position1 = new Position();
        position1.id = 1;
        position1.name = Position.PositionName.DEV;

        Position position2 = new Position();
        position2.id = 2;
        position2.name = Position.PositionName.TEST;

        Position position3 = new Position();
        position3.id = 3;
        position3.name = Position.PositionName.SCRUM_MASTER;

        Position position4 = new Position();
        position4.id = 1;
        position4.name = Position.PositionName.PM;


        Account account1 = new Account();
        account1.id = 1;
        account1.email = "Email 1";
        account1.username = "Username 1";
        account1.fullName = "FullName 1";
        account1.department = department1;
        account1.position = null;
        account1.createDate = LocalDate.of(2020, 1, 1);

        Account account2 = new Account();
        account2.id = 2;
        account2.email = "Email 2";
        account2.username = "Username 2";
        account2.fullName = "FullName 2";
        account2.department = null;
        account2.position = position2;
        account2.createDate = LocalDate.of(2020, 1, 1);

        Account account3 = new Account();
        account3.id = 3;
        account3.email = "Email 3";
        account3.username = "Username 3";
        account3.fullName = "FullName 3";
        account3.department = department3;
        account3.position = position3;
        account3.createDate = LocalDate.of(2020, 1, 1);

        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Group1";
        group1.creator = account1;
        group1.createDate = LocalDate.of(2020, 1, 1);

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "Group2";
        group2.creator = account2;
        group2.createDate = LocalDate.of(2020, 1, 1);

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "Group3";
        group3.creator = account3;
        group3.createDate = LocalDate.of(2020, 1, 1);

        Account[] accounts = new Account[]{account1, account2, account3};
        group1.accounts = accounts;

        Department[] departments = new Department[]{department1, department2, department3};

        Group[] groups = new Group[]{group1};
        account2.groups = groups;// các nhóm mà acc này tham gia

        // groupAccount này cho biêt là account 1 đang tham gia vào nhóm 1
        GroupAccount groupAccount1 = new GroupAccount();
        groupAccount1.account = account1;
        groupAccount1.group = group1;

        GroupAccount groupAccount2 = new GroupAccount();
        groupAccount2.account = account1;
        groupAccount2.group = group2;

        GroupAccount groupAccount3 = new GroupAccount();
        groupAccount3.account = account1;
        groupAccount3.group = group3;

        GroupAccount[] groupAccounts = {groupAccount1, groupAccount2, groupAccount3};


        Exercise1.question1(account2);
        Exercise1.question1(account1);
        Exercise1.question1(account3);

//        Question 2:
//        Kiểm tra account thứ 2
//        Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
//        Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
//        Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
//        Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
        // dùng for để dyuệt qua array  groupAccounts để đếm xem account này tham gia baoi nhiêu nhóm
//        System.out.println("================== Question 2: ================== ");
//        int count = account2.groups.length;
//        for (GroupAccount ga: groupAccounts) {
//            if (ga.account.id == account1.id) {
//                count++;
//            }
//        }
//        if (count == 0) {
//            System.out.println("Nhân viên này chưa có group");
//        } else if (count == 1 || count == 2) {
//            System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
//        } else if (count == 3) {
//            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
//        } else {
//            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
//        }


//        Question 3:
//        Sử dụng toán tử ternary để làm Question 1
//        System.out.println("==================  Question 3: ================== ");
//        System.out.println(account2.department == null ? "Nhân viên này chưa có phòng ban" : "Phòng ban của nhân viên này là " + account2.department.name);

//        Question 4:
//        Sử dụng toán tử ternary để làm yêu cầu sau:
//        Kiểm tra Position của account thứ 1
//        Nếu Position = Dev thì in ra text "Đây là Developer"
//        Nếu không phải thì in ra text "Người này không phải là Developer"
//        System.out.println("==================  Question 4: ================== ");
//        System.out.println(account1.position != null &&  account1.position.name == Position.PositionName.DEV ? "Đây là Developer" : "Người này không phải là Developer");
        // để là dev thì phải có gtri chức vụ   +   chức vụ =  DEV

        // in ra tên phòng ban của account thứ nhất
//        System.out.println(account1.position.name);// null thì ko có gtri nên ko thể .id hoặc .name
//        System.out.println(null.name);


//        Question 5:
//        Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau:
//        Nếu = 0 thì in ra nhóm chưa có thành viên nào
//        Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
//        Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
//        Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
//        Còn lại in ra "Nhóm có nhiều thành viên"

//        System.out.println("================== Question 5: ================== ");
//        // tương tự bài số 2
//        int count = group1.accounts.length;
//        for (GroupAccount ga: groupAccounts) {
//            if (ga.group.id == group1.id) {
//                count++;
//            }
//        }
//        if (count == 0) {
//            System.out.println("Nhóm chưa có thành viên");
//        } else if (count == 1) {
//            System.out.println("Nhóm có 1 thành viên");
//        } else if (count == 2) {
//            System.out.println("Nhóm có 2 thành viên");
//        } else if (count == 3) {
//            System.out.println("Nhóm có 3 thành viên");
//        } else {
//            System.out.println("Nhóm có nhiều thành viên");
//        }

//        Question 6:
//        Sử dụng switch case để làm lại Question 2
//        System.out.println("================== Question 6: ================== ");
//        int count = account2.groups.length;
//        for (GroupAccount ga: groupAccounts) {
//            if (ga.account.id == account1.id) {
//                count++;
//            }
//        }
//        switch (count) {
//            case 0:
//                System.out.println("Nhân viên này chưa có group");
//                break;
//            case 1:
//            case 2:
//                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
//                break;
//            case 3:
//                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
//                break;
//            default:
//                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
//        }

//        Question 7:
//        Sử dụng switch case để làm lại Question 4
//        System.out.println("==================  Question 7: ================== ");
//        if (account1.position == null) {
//            System.out.println("Người này không phải là Developer");
//        } else {
//            Position.PositionName pName = account1.position.name;
//            switch (pName) {
//                case DEV:
//                    System.out.println("Đây là Developer");
//                    break;
//                default:
//                    System.out.println("Người này không phải là Developer");
//            }
//        }


//        FOREACH
//        Question 8:
//        In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ
//        for (Account account : accounts) {
//            System.out.println("ID: " + account.id);
//            System.out.println("Username: " + account.username);
//            System.out.println("FullName: " + account.fullName);
//            System.out.println("Email: " + account.email);
//            System.out.println("DepartmentName: " + (account.department != null ? account.department.name : "Không có"));
//            System.out.println("PositionName: " + (account.position != null ? account.position.name : "Không có"));
//        }
//        Question 9:
//        In ra thông tin các phòng ban bao gồm: id và name
//
//                FOR
//        Question 10:
//        In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ theo định dạng như sau:
//        Thông tin account thứ 1 là:
//        Email: NguyenVanA@gmail.com
//        Full name: Nguyễn Văn A
//        Phòng ban: Sale
//
//        Thông tin account thứ 2 là:
//        Email: NguyenVanB@gmail.com
//        Full name: Nguyễn Văn B
//        Phòng ban: Marketting
//
//        Question 11:
//        In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
//        Thông tin department thứ 1 là:
//        Id: 1
//        Name: Sale
//        Thông tin department thứ 2 là:
//        Id: 2
//        Name: Marketing
//
//        Question 12:
//        Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10
//        for (int i = 0; i < departments.length; i++) {
//            if (i < 2) {
//                System.out.println("Department ID : " + departments[i].id + ", Department Name : " + departments[i].name);
//            }
//        }

//        Question 13:
//        In ra thông tin tất cả các department ngoại trừ department thứ 2
//        for (int i = 0; i < departments.length; i++) {
//            if (i != 1) {
//                System.out.println("Department ID : " + departments[i].id + ", Department Name : " + departments[i].name);
//            }
//        }
//        Question 14:
//        In ra thông tin tất cả các account có id < 2
//        for (Account account : accounts) {
//            if (account.id < 2) {
//                System.out.println("ID: " + account.id);
//                System.out.println("Username: " + account.username);
//                System.out.println("FullName: " + account.fullName);
//                System.out.println("Email: " + account.email);
//                System.out.println("DepartmentName: " + (account.department != null ? account.department.name : "Không có"));
//                System.out.println("PositionName: " + (account.position != null ? account.position.name : "Không có"));
//            }
//        }
//        Question 15:
//        In ra các số chẵn nhỏ hơn hoặc bằng 20
//        for (int i = 0; i <= 20; i++) {
//            if (i %2 == 0) {
//                System.out.println(i);
//            }
//        }

    }
}