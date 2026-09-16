import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        // tạo ra các department
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Sale";
        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Marketing";
        Department department3 = new Department();
        department3.id = 3;
        department3.name = "Bảo vệ";

        System.out.println("Department ID: " + department1.id);
        System.out.println("Department Name: " + department1.name);
        System.out.println("=======================");

        // tạo ra các position
        Position position1 = new Position();
        position1.id = 1;
        position1.name = PositionName.DEV;

        Position position2 = new Position();
        position2.id = 2;
        position2.name = PositionName.TEST;

        Position position3 = new Position();
        position3.id = 3;
        position3.name = PositionName.SCRUM_MASTER;

        Position position4 = new Position();
        position4.id = 4;
        position4.name = PositionName.PM;

        System.out.println("Position ID: " + position1.id);
        System.out.println("Position Name: " + position1.name);
        System.out.println("=======================");

        // tạo ra các account
        Account account1 = new Account();
        account1.id = 1;
        account1.username = "dongnv";
        account1.fullName = "Nguyễn Viết Đồng";
        account1.email = "dongnv@gmail.com";
        account1.department = department1;
        account1.position = position1;
        account1.createDate = LocalDate.now();
        Account[] accounts = new  Account[]{account1, account1, account1};

//        Question 14:
//        In ra thông tin tất cả các account có id < 4
        for (Account account : accounts) {
            if (account.id < 4) {
                // in thông tin
            }
        }
//
//        Question 15:
//        In ra các số chẵn nhỏ hơn hoặc bằng 20
        for (int i = 0; i < 20; i++) {
            if (i %2 == 0) {
                System.out.println(i);
            }
        }


    }
}
