package backend.repository.impl;

import backend.repository.ICanBoRepository;
import entity.*;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CanBoRepositoryImpl implements ICanBoRepository {
    @Override
    public List<CanBo> findAll() {
        List<CanBo> canBos = new ArrayList<>();
        try {
            // tạo kết nối đến Database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from can_bo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// thư thi câu query(select) sau
            while (resultSet.next()) {// chuyển từ  resultSet thành list cán bộ
                String hoTen = resultSet.getString("ho_ten");// lấy dữ lieju theo tên cột hoặc vtri
                int tuoi = resultSet.getInt("tuoi");
                String gt = resultSet.getString("gioi_tinh");
                GioiTinh gioiTinh = GioiTinh.valueOf(gt);// chuyển từ String thành enum
                String diaChi = resultSet.getString("dia_chi");
                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString);// chuyển từ String thành enum
                if (loai == Loai.CN) {
                    int bac = resultSet.getInt("bac");
                    CanBo cn = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                    canBos.add(cn);
                } else if (loai == Loai.KS) {
                    String nghanhDaoTao = resultSet.getString("nganh");
                    CanBo ks = new KySu(hoTen, tuoi, gioiTinh, diaChi, Loai.KS, nghanhDaoTao);
                    canBos.add(ks);
                } else if (loai == Loai.NV) {
                    String congViec = resultSet.getString("cong_viec");
                    CanBo nv = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, Loai.NV, congViec);
                    canBos.add(nv);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {// cụm này luôn thực hien cuối cùng
            JDBCUtils.closeConnection();
        }
        return canBos;
    }
}
