import java.time.LocalDate;

public class Account {
    int id;
    String username;
    String fullName;
    String email;
    // department_id và position_id   khóa ngoại
    // với khóa ngoại thì sẽ chuyển thành Object
    Department department; //department_id
//    int departmentId;// join
    Position position; //position_id

    LocalDate createDate;


    public void in() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + username);
    }
}
