import java.util.Random;
import java.util.Scanner;

public class Main3 {
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

        Department department4 = new Department();
        department4.id = 4;
        department4.name = "ABC";

        Department department5 = new Department();
        department5.id = 5;
        department5.name = "XYZ";

        Department department6 = new Department();
        department6.id = 6;
        department6.name = "123";

        Department[] departments = {department1, department2, department3, department4, department5, department6};

        // continue: bỏ qua lần lặp hiện tại và chuyển qua lần lặp tiếp theo mà ko làm các hành động khác , tăng i lên 1 đơn vị
        // break:   dừng luôn vòng lại tại vị trí break,  và vẫn chạy các câu lệnh khác của method
        // return;  dừng luôn method
        // in ra gặp gtri 5 thì dừng luôn method

//        1 2 3 4 5 6 7 8 9 10
//        for (int i = 1; i <= 10; i++) {// for chỉ chạy từ 1-5  5s
//            if (i == 5) {
//                continue;
//            }
//            System.out.println(i);
//        }
//        System.out.println("Hello");

        // i++: tăng i lên 1 đơn vị
        // ++i  tăng i lên 1 đơn vị
        // i++ và ++i khác gì nhau
        // nếu ++ đứng sau biến   thì thứ tự thực hiện:    gán  rồi mới tăng
        // nếu ++ đứng trước biến   thì thứ tự thực hiện:    tăng  rồi mới gán
//        int a = 1;// gán gtri 1 vào cho a
//        // int b = a++;// 2 hành động:  1. gán   2. tăng gtri
//        int b = a;// gán   1
//        a = a + 1;// tăng   2
//        System.out.println("a = " + a );
//        System.out.println("b = " + b );
//
//        int x = 1;
////        int y = ++x;// 2 hành động:  1. gán   2. tăng gtri
//        x = x + 1;  // 2
//        int y = x;  // 2

//        System.out.println("x = " + x );
//        System.out.println("y = " + y );

//        int a = 1;
//        a += 3;
//
//        boolean b = false;
//        System.out.println(!b);
        // hiển thị ngày hiện tại theo nước Pháp
//        Locale locale = new Locale("jp", "JP");
//        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
//        String date = dateFormat.format(new Date());
//        System.out.println(date);

        // hiển thị theo format
//        System.out.println(new Date());
//
//        String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date = simpleDateFormat.format(new Date());
//        System.out.println(date);


//        System.out.print("VTI\n");
//        System.out.println("Hello");// line; in xong thì xuống dòng
//        System.out.print("VTI");

//        System.out.printf("%s ------- %s ----- %d ----- %f ", "Hello", "VTI", 2026, 5.5);  // in theo format
        // string    string
        // departments
//        for (Department department : departments) {
//            System.out.println("Department ID: " + department.id + ", department name: " + department.name);
//        }
        /*System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Department Name");
        System.out.println("+-----+--------------------+");
        for (Department department : departments) {
            System.out.printf("|%5d|%20s|\n", department.id, department.name);
        }
        System.out.println("+-----+--------------------+");*/

//        Random rand = new Random();// random số
//        // random ngẫu nhiên
//        int x = rand.nextInt();
////        System.out.println(x);
//        // random từ 0-10
//        int y = rand.nextInt(11);
////        System.out.println(y);
//        // random từ 10-20
//        int z = rand.nextInt(10, 21);
//        System.out.println(z);


        // in ra họ tên
//        String name = "Nguyễn Văn A";// fix cứng
//        System.out.println(name);
        // nhập dữ liệu từ bàn phím
        Scanner sc = new Scanner(System.in);
//        System.out.println("Nhập vào họ tên:");
//        String name = sc.nextLine();// sc.nextLine(): nhận dữ liệu nhập từ bàn phím
//        System.out.println("Tên bạn vừa nhập là: " + name);

        System.out.println("Nhập tuổi:");
        while (true) {
            if (sc.hasNextInt() == true) {// kiểm tra dữ liệu nhập vào có phải số nguyên ko
                int age = sc.nextInt();
                System.out.println("Tuổi vừa nhập là:  " + age);
                break;
            } else {
                System.out.println("Nhập sai định dạng. Nhập lại: ");
            }
            sc.nextLine();
        }



    }
}
