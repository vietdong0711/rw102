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

        System.out.println("Account ID: " + account1.id);
        System.out.println("Username: " + account1.username);
        System.out.println("FullName: " + account1.fullName);
        System.out.println("Email: " + account1.email);
        System.out.println("CreateDate: " + account1.createDate);
        System.out.println("Department ID: " + account1.department.id);
        System.out.println("Department Name: " + account1.department.name);
        System.out.println("Position ID: " + account1.position.id);
        System.out.println("Position Name: " + account1.position.name);
        System.out.println("=======================");

    }
}
