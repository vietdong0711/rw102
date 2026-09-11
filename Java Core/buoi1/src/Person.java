import java.time.LocalDate;

public class Person {
    // thuộc tính: giống các cột trong sql
    int id;// auto_increment
    String fullName;
    Gender gender;// enum
    LocalDate birthDate;
    String cccd;
    boolean isPassCourse;

    int[] points; // ds điểm: trong sql ko có arrays

    // phương thức: hành của đối  tượng: giống procedure
    void an() {
        System.out.println("Person đang ăn");
    }

    void ngu() {
        System.out.println("Person đang ngủ");
    }

    void inThongTin() {
        System.out.println("ID: " + id);
        System.out.println("fullName: " + fullName);
        System.out.println("gender: " + gender);
        System.out.println("birthDate: " + birthDate);
    }
}
