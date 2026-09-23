import java.util.Arrays;

public class Exercise5 {

//    Question 5:
//    So sánh 2 phòng ban thứ 1 và phòng ban thứ 2 xem có bằng nhau không
//    (bằng nhau khi tên của 2 phòng ban đó bằng nhau)
    public static void question5(Department dep1, Department dep2) {
        // check null
        if (dep1.name.equals(dep2.name)) {
            System.out.println("Hai phòng ban này bằng nhau");
        } else {
            System.out.println("Hai phòng ban này không bằng nhau");
        }
    }

//    Question 6:  sawsp xép chữ cái đầu tiên của từng tên
//    Khởi tạo 1 array phòng ban gồm 5 phòng ban, sau đó in ra danh sách phòng ban theo thứ tự tăng dần theo tên (sắp xếp theo vần ABCD)
//    VD:
//    Accounting
//    Boss of director
//            Marketing
//    Sale
//    Waiting room
    public static void question6(String[] names) {
        for (int i = 0; i < names.length-1; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (names[i].compareToIgnoreCase(names[j]) >0 ) {// Accounting   Abc
                    // đổi cho v trí i cho vtri J
                    String temp = names[i];
                    names[i] = names[j];
                    names[j] = temp;
                }
            }
        }
        for (String s : names) {
            System.out.println(s);
        }
    }

//    Question 7: sắp xếp chữ cái đầu tiên của từng từ cuối cùng trong tên
//    Khởi tạo 1 array học sinh gồm 5 Phòng ban, sau đó in ra dan sách phòng ban được sắp xếp theo tên
//    VD:
//    Accounting
//    Boss of director
//            Marketing
//    waiting room
//    Sale
    public static void question7(String[] names) {
        for (int i = 0; i < names.length - 1; i++) {
            for (int j = i + 1; j < names.length; j++) {
                // lấy ra từ cuối cùng trong tên
                String[] arrs1 = names[i].split(" ");
                String[] arrs2 = names[j].split(" ");
                String last1 = arrs1[arrs1.length-1];
                String last2 = arrs2[arrs2.length-1];

                if (last1.compareToIgnoreCase(last2) > 0) {// Accounting   Abc
                    // đổi cho v trí i cho vtri J
                    String temp = names[i];
                    names[i] = names[j];
                    names[j] = temp;
                }
            }
        }
        for (String s : names) {
            System.out.println(s);
        }
    }

}
