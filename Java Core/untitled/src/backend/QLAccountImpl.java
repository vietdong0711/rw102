package backend;

import entity.Department;
import entity.Position;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccountImpl implements IQLAccount {
    private Scanner scanner;

    public QLAccountImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void themMoi() {
        System.out.println("==== CHỨC NĂNG THÊM MỚI ====");
        System.out.println("Nhập username: ");
        String username = scanner.nextLine();
        System.out.println("Nhập emai: ");
        String email = scanner.nextLine();
        System.out.println("Nhập fullName: ");
        String fullName = scanner.nextLine();
        // department
        List<Department> departments = this.getAllDepartment();
        List<Position> positions = this.getAllPosition();
        int depId;
        int posId;
        while (true) {
            System.out.println("Nhập department ID:");
            System.out.println("+-----+--------------------+");
            System.out.printf("|%5s|%20s|\n", "ID", "Name");
            System.out.println("+-----+--------------------+");
            for (Department dep : departments) {
                System.out.printf("|%5s|%20s|\n", dep.getId(), dep.getName());
            }
            System.out.println("+-----+--------------------+");
            depId = scanner.nextInt();
            scanner.nextLine();
            boolean check = false;
            for (Department dep : departments) {
                if (dep.getId() == depId) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            } else {
                System.err.println("ID phòng ban chưa đúng. Nhập lại:");
            }
        }

        while (true) {
            System.out.println("Nhập position ID:");
            System.out.println("+-----+--------------------+");
            System.out.printf("|%5s|%20s|\n", "ID", "Name");
            System.out.println("+-----+--------------------+");
            for (Position pos : positions) {
                System.out.printf("|%5s|%20s|\n", pos.getId(), pos.getName());
            }
            System.out.println("+-----+--------------------+");
            posId = scanner.nextInt();
            scanner.nextLine();
            boolean check = false;
            for (Position pos : positions) {
                if (pos.getId() == posId) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            } else {
                System.err.println("ID chức vụ chưa đúng. Nhập lại:");
            }
        }

        System.out.printf("DepID: %d,   PosID: %d", depId, posId);
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO account (`email`, `username`, `full_name`, `department_id`, `position_id`) \n" +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, fullName);
            statement.setInt(4, depId);
            statement.setInt(5, posId);

            int c = statement.executeUpdate();
            if (c > 0) {
                System.out.println("thêm mới thành công");
            } else {
                System.out.println("Thêm mới thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }


    }

    private List<Department> getAllDepartment() {
        List<Department> departments = new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from department";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("department_id");
                String name = rs.getString("department_name");
                Department department = new Department(id, name);

                departments.add(department);
//                departments.add(new Department(rs.getInt("department_id"), rs.getString("department_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }

        return departments;
    }

    private List<Position> getAllPosition() {
        List<Position> positions = new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from position";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                // string -> enum
                Position.PositionName pName = Position.PositionName.valueOf(name);
                Position position = new Position(id, pName);

                positions.add(position);
//                positions.add(new Position(rs.getInt("position_id"), Position.PositionName.valueOf(rs.getString("position_name"))) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }

        return positions;
    }


}
