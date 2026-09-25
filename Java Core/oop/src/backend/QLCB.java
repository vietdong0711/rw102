package backend;

import entity.CanBo;
import entity.GioiTinh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLCB implements IQLCB {

//    private CanBo[] canBos = new CanBo[1000];// lưu trữ dữ lieu, fix cứng số lượng lưu tru
    private List<CanBo> canBoList;
    private Scanner sc = new Scanner(System.in);

    public QLCB() {
        canBoList = new ArrayList<>();
        canBoList.add(new CanBo("nguyen van a", 20, GioiTinh.NAM, "HN"));
        canBoList.add(new CanBo("tran van b", 21, GioiTinh.NU, "ĐN"));
        canBoList.add(new CanBo("le thi c", 22, GioiTinh.KHAC, "HCM"));
        canBoList.add(new CanBo("nguyen ba d", 23, GioiTinh.NAM, "HN"));
    }

    @Override
    public void themMoi() {
        System.out.println("==== THÊM MỚI CÁN BỘ ====");
    }

    @Override
    public void timKiemTheoTen() {
        System.out.println("==== TÌM KIẾM CÁN BỘ ====");
        System.out.println("Nhập họ tên cần tìm: ");
        String ten = sc.nextLine();
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBoList) {
            if (cb.getHoTen().contains(ten)) {  // like %ten%
                System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
            }
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");
    }

    @Override
    public void hienThiToanBo() {
        System.out.println("==== HIỂN THỊ TOÀN BỘ CÁN BỘ ====");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBoList) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");
    }

    @Override
    public void xoaTheoTen() {
        System.out.println("==== XÓA CÁN BỘ ====");
    }
}
