import java.time.LocalDate;

public class Account {
    // sửa tên thuộc tính   shift + F6
    int id;
    String email;
    String username;
    String fullName;
    Department department;//phòng ban của account đó đang tham gia
    Position position;
    LocalDate createDate;

    Group[] groups;// ds các nhóm mà acc tham gia
}