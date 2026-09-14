import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        //  ctrl + alt + L : format lại code
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
        account1.position = position1;
        account1.createDate = LocalDate.of(2020, 1, 1);

        System.out.println("Hiển thị thông tin department");
        System.out.println("Department ID: " + department1.id);
        System.out.println("Department Name: " + department1.name);
        System.out.println("=============");
        System.out.println(department1.toString());

        System.out.println(position1.toString());
    }
}
