public class Exercise1 {

    // xác định tham số đàu vào  Account
    public static void question1(Account account) {
        if (account.department == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là " + account.department.name);
        }
    }

    public static void question2(Account account) {
        if (account.department == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là " + account.department.name);
        }
    }

    public static void question3(Account account) {
        if (account.department == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là " + account.department.name);
        }
    }
}
