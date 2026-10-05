package frontend;

import backend.IQLCB;
import backend.QLCB;
import backend.controller.CanBoController;
import entity.CanBo;

import java.util.List;
import java.util.Scanner;

public class Function {
    private CanBoController canBoController;

    public Function() {
        this.canBoController = new CanBoController();
    }

    // them mới

    // hien thi
    public void hienThiToanBo() {
        // yêu cầu controller trả ra 1 ds các cán bộ
        List<CanBo> canBos = canBoController.findAll();

        System.out.println("==== HIỂN THỊ TOÀN BỘ CÁN BỘ ====");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBos) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");

    }

    // tim kiem

    // xoa

    // update

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm mới cán bộ.");
            System.out.println("2. Tìm kiếm theo họ tên.");//
            System.out.println("3. Hiển thị toàn bộ các cán bộ.");//
            System.out.println("4. Nhập vào tên của cán bộ và xóa cán bộ đó.");// nhập đúng tên
            System.out.println("5. Update tên địa chỉ theo tên(nhập đúng).");
            System.out.println("6. Thoát khỏi chương trình.");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    this.hienThiToanBo();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    System.out.println("Thoát.");
                    System.exit(0);
                default:
                    System.out.println("Chọn sai, Chọn lại!");
            }
        }

    }
}
