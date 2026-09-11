import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public enum GioioiTinh {
        NAM, NU, KHAC
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        // biến   SET @min INT
        // varchar     String
        String fullName = "Nguyễn Viết Đồng";// varchar: chứa  bao nheieu kí tự cx dc
        char gender = 'M';// chỉ chứa dc 1 kí tự


        int age = 27;// int
        long a = 1;
        short b = 1;
        byte c = 1;
        Gender gender1 = Gender.MALE;

        float point = 7.5f;// float
        double d = 7.5;


        LocalDate birthday = LocalDate.of(1999, 11, 11);// date
        LocalDateTime date1 = LocalDateTime.now();// datetime
        Date date = new Date();



        System.out.println("FullName: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Point: " + point);
        System.out.println("Birthday: " + birthday);
        System.out.println("Date: " + date);
        System.out.println("DateTime: " + date1);
        System.out.println("Gender: " + gender1);
        System.out.println("Gender: " + Gender.FEMALE);
        System.out.println("Giới tính: " + GioioiTinh.NAM);

        // arrays : khi muốn biểu diễn 1 danh sách chứa các ptu cùng kiểu dữ liệu
        // 1 học sinh có điểm các môn lần lượt là 10 9 8 7
        int[] points = new int[]{10, 9, 8, 7};
        int[] points2 = new int[4];// danh  này chỉ có 4 ptu
        points2[0] = 10;
        points2[1] = 9;
        points2[2] = 8;
        points2[3] = 7;


        // 1 học sinh có điểm các môn lần lượt là 10     9.5     8.3     7.1
        int i1 = 10;// số nguyên
        double p1 = 10;// số thực   10    10.0
        double p2 = 9.5;
        double p3 = 8.3;
        double p4 = 7.1;
        // 30 điểm thfi phải khai báo biến 30 lần
        double[] diems = new double[]{10, 9.5, 8.3, 7.1, 1.1f};
        String[] hocSinhs = new String[]{"An", "Anh", "Danh", "Phúc", "1.1"};

        // kiểu đúng sai
        boolean check1 = false;
        boolean check2 = true;

        // so sánh
        boolean check3 =  (1 <= 2);
        System.out.println("check3: " + check3);

        // khai báo 1 biến tuổi và in ra
//        int age = 10; // ??? a là gì
//
//
//        System.out.println(age);
        
        


    }
}