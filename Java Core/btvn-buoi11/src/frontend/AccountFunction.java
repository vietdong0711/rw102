package frontend;

import backend.controller.AccountController;
import backend.controller.DepartmentController;
import backend.controller.PositionController;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AccountFunction {
    private AccountController accountController;
    private DepartmentController departmentController;
    private PositionController positionController;
    private Scanner scanner;

    public AccountFunction() {
        this.accountController = new AccountController();
        this.departmentController = new DepartmentController();
        this.positionController = new PositionController();
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Hiển thị toàn bộ account");
            System.out.println("2. Thêm account");
            System.out.println("3. Xóa account theo id");
            System.out.println("4. Update account theo id");
            System.out.println("5. Thoát");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.findAll();
                    break;
                case "2":
                    this.create();
                    break;
                case "3":
                    this.delete();
                    break;
                case "4":
                    this.update();
                    break;
                case "5":
                    return;
//                    System.exit(0);
                default:
                    System.out.println("Chọn sai! Chọn lại!");
            }
        }
    }

    private void update() {
        System.out.println("==== CHỨC NĂNG UPDATE ACCOUNT ====");
        System.out.println("Mời bạn nhập ID account muốn update thông tin: ");
        int id = 0;
        while (true) {
            if (scanner.hasNextInt()) {// 1 kiem tra xem co phai la so ko
                id = scanner.nextInt();
                scanner.nextLine();
                if (id <= 0) {  // 2 kiem tra <= 0
                    System.err.println("Vui lòng nhập số > 0: ");
                } else {
                    // 3 kiem tra id  co ton tai ko
                    boolean checkExists = accountController.checkIdExists(id);
                    if (!checkExists) {
                        System.err.println("ID này không tồn tại! Nhập lại: ");
                    } else {
                        break;
                    }
                }
            } else {
                System.err.println("Vui lòng nhập số! Nhập lại: ");
                scanner.nextLine();
            }
        }

        String username;
        while (true) {
            System.out.println("Nhập username: ");
            username = scanner.nextLine();
            if (username.trim().length() < 5 || username.trim().length() > 50) {
                System.err.println("Nhập username dài từ 5-50 kí tự");
                continue;
            }
            if (accountController.checkUsernameExists(username, id)) {
                System.err.println("Username này đã tồn tại");
                continue;
            }
            break;
        }

        boolean check = accountController.update(id, username);
        if (check) {
            System.out.println("Update successfully!");
        } else {
            System.out.println("Update failed!");
        }
    }

    private void delete() {
        System.out.println("==== CHỨC NĂNG XÓA ACCOUNT ====");
        System.out.println("Mời bạn nhập ID account muốn xóa: ");
        int id = 0;
        while (true) {
            if (scanner.hasNextInt()) {// 1 kiem tra xem co phai la so ko
                id = scanner.nextInt();
                scanner.nextLine();
                if (id <= 0) {  // 2 kiem tra <= 0
                    System.err.println("Vui lòng nhập số > 0: ");
                } else {
                    // 3 kiem tra id  co ton tai ko
                    boolean checkExists = accountController.checkIdExists(id);
                    if (!checkExists) {
                        System.err.println("ID này không tồn tại! Nhập lại: ");
                    } else {
                        break;
                    }
                }
            } else {
                System.err.println("Vui lòng nhập số! Nhập lại: ");
                scanner.nextLine();
            }
        }

        // xóa
        boolean canDelete = accountController.delete(id);
        if (canDelete) {
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Delete failed!");
        }
    }

    private void create() {
        System.out.println("====CHỨC NĂNG TẠO MỚI ACCOUNT ====");
        Account account = new Account();

        while (true) {
            System.out.println("Nhập username: ");
            String username = scanner.nextLine();
            if (username.trim().length() < 5 || username.trim().length() > 50) {
                System.err.println("Nhập username dài từ 5-50 kí tự");
                continue;
            }
            if (accountController.checkUsernameExists(username, null)) {
                System.err.println("Username này đã tồn tại");
                continue;
            }
            account.setUsername(username);
            break;
        }

        while (true) {
            System.out.println("Nhập fullname: ");
            String fullName = scanner.nextLine();
            if (fullName.trim().length() < 5 || fullName.trim().length() > 50) {
                System.err.println("Nhập fullname dài từ 5-50 kí tự");
                continue;
            }
            account.setFullName(fullName);
            break;
        }

        while (true) {
            System.out.println("Nhập email: ");
            String email = scanner.nextLine();
            if (email.trim().length() < 5 || email.trim().length() > 50) {
                System.err.println("Nhập email dài từ 5-50 kí tự");
                continue;
            }
            // a@lgcns.com
            if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {// dong@gmail.com
                System.err.println("email không đúng định dạng");
                continue;
            }
            if (accountController.checkEmailExists(email, null)) {
                System.err.println("email này đã tồn tại");
                continue;
            }
            account.setEmail(email);
            break;
        }

        List<Department> departments = departmentController.findAll();

        while (true) {
            Department department = null;
            System.out.println("Chọn ID phòng ban muốn thêm vào: ");

            for (Department dep : departments) {
                System.out.printf("ID: %s - Name: %s\n", dep.getId(), dep.getName());
            }
            if (scanner.hasNextInt()) {
                int depId = scanner.nextInt();
                scanner.nextLine();
                if (depId <= 0) {
                    System.err.println("DepartmentID phải lớn hơn 0! Nhập lại: ");
                    continue;
                }

                // tìm depId có tồn tại ko
                for (Department dep : departments) {
                    if (dep.getId() == depId) {
                        department = dep;
                        break;
                    }
                }
                if (Objects.isNull(department)) {
                    System.out.println("DepartmentID ko tồn tại! Nhập lại: ");
                } else {
                    account.setDepartment(department);
                    break;
                }
            } else {
                System.out.println("Vui lòng nhập số. Chọn lại phòng ban!");
                scanner.nextLine();
            }
        }

        List<Position> positions = positionController.findAll();
        while (true) {
            Position position = null;
            System.out.println("Chọn ID chức vụ muốn thêm vào: ");
            for (Position pos : positions) {
                System.out.printf("ID: %s - Name: %s\n", pos.getId(), pos.getName());
            }
            if (scanner.hasNextInt()) {
                int posId = scanner.nextInt();
                scanner.nextLine();

                for (Position pos : positions) {
                    if (pos.getId() == posId) {
                        position = pos;
                        break;
                    }
                }
                if (Objects.isNull(position)) {
                    System.out.println("PositionID ko tồn tại. Nhập lại: ");
                } else {
                    account.setPosition(position);
                    break;
                }
            } else {
                System.out.println("Chọn sai. Chọn lại chức vụ!");
                scanner.nextLine();
            }
        }

        boolean check = accountController.create(account);// username, fullName, email,depID, posID
        if (check) {
            System.out.println("Create successfully!");
        } else {
            System.out.println("Create failed!");
        }
    }

    private void findAll() {
        System.out.println("==== HIỂN THỊ TOÀN BỘ ACCOUNT ====");
        List<Account> accounts = accountController.findAll();
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n", "ID", "Username", "Full Name", "Email", "Department", "Position");
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
        for (Account acc : accounts) {
            String depName = Objects.nonNull(acc.getDepartment()) ? acc.getDepartment().getName() : "";
            String posName = Objects.nonNull(acc.getPosition()) ? acc.getPosition().getName().name() : "";
            System.out.printf("|%5s|%25s|%25s|%25s|%25s|%25s|\n"
                    , acc.getId(), acc.getUsername(), acc.getFullName(), acc.getEmail()
                    , depName, posName);
        }
        System.out.println("+-----+-------------------------+-------------------------+-------------------------+-------------------------+-------------------------+");
    }

}
