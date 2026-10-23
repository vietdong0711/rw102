package com.vti.backend.service.impl;

import com.vti.backend.common.StringCommon;
import com.vti.backend.repository.IAccountRepository;
import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountServiceImpl implements IAccountService {

    private IAccountRepository accountRepository;
    private IDepartmentRepository departmentRepository;
    private IPositionRepository positionRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepositoryImpl();
        this.departmentRepository = new DepartmentRepositoryImpl();
        this.positionRepository = new PositionRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(Account account) {
        return accountRepository.create(account);
    }

    @Override
    public boolean update(int id, String username) {
        return accountRepository.update(id, username);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public boolean checkUsernameExists(String username, Integer id) {
        return accountRepository.checkUsernameExists(username, id);
    }

    @Override
    public boolean checkEmailExists(String email, Integer id) {
        return accountRepository.checkEmailExists(email, id);
    }

    @Override
    public boolean checkIdExists(int id) {
        return accountRepository.checkIdExists(id);
    }

    @Override
    public List<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public String importCSV(String path) {
        File file = new File(path);
        if (!path.endsWith(".csv")) {
            return "File ko đúng định dạng !";
        }
        if (!file.exists()) {
            return "File ko tồn tại !";
        }

        // đọc file
        String header = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Account> accounts = accountRepository.findAll();
            Map<String, Account> mapByUsername = accounts.stream()
                    .collect(Collectors.toMap(acc -> acc.getUsername(), acc -> acc));
            Map<String, Account> mapByEmail = accounts.stream()
                    .collect(Collectors.toMap(acc -> acc.getEmail(), acc -> acc));

            List<Department> departments = departmentRepository.findAll();
            Map<Integer, Department> mapByDepId = departments.stream()
                    .collect(Collectors.toMap(dep -> dep.getId(), dep -> dep));

            List<Position> positions = positionRepository.findAll();
            Map<Integer, Position> mapByPosId = positions.stream()
                    .collect(Collectors.toMap(pos -> pos.getId(), pos -> pos));

            String line;
            List<Account> accountImports = new ArrayList<>();
            header = br.readLine();
            List<String> errorsRow = new ArrayList<>();// ds các row bị lỗi

            while ((line = br.readLine()) != null) {
                List<String> errors = new ArrayList<>();// ds lỗi của row đó
                // lấy dữ liệu
                String[] arrs = line.split(",", -1);
                String email = arrs[0];//5-50 đúng định dạng,unique(dùng map -> lấy ra toàn bộ email),
                String username = arrs[1];//5-50 unique(dùng map -> lấy ra toàn bộ username), ko có kí tự đặc biệt
                String fullName = arrs[2];// 5-50, ko có kí tự đặc biệt
                String depIdStr = arrs[3];// tồn tại, chỉ dc nhập số
                String posIdStr = arrs[4];// tồn tại, chỉ dc nhập số

                // validation email
                if (Objects.isNull(email) || email.trim().isEmpty()
                        || email.trim().length() < 5 || email.trim().length() > 50) {
                    errors.add("Email phải từ 5-50 kí tự");
                }
                if (!email.trim().matches(StringCommon.EMAIL_REGEX)) {
                    errors.add("Email ko đúng định dạng");
                }
                if (mapByEmail.get(email) != null) {
                    errors.add("Email đã tồn tại");
                }

                // validation username
                if (Objects.isNull(username) || username.trim().isEmpty()
                        || username.trim().length() < 5 || username.trim().length() > 50) {
                    errors.add("Username phải từ 5-50 kí tự");
                }
                if (!username.trim().matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
                    errors.add("Username chỉ được chứa số và chữ");
                }
                if (mapByUsername.get(username) != null) {
                    errors.add("Username đã tồn tại");
                }

                // validation fullname
                if (Objects.isNull(fullName) || fullName.trim().isEmpty()
                        || fullName.trim().length() < 5 || fullName.trim().length() > 50) {
                    errors.add("Fullname phải từ 5-50 kí tự");
                }
                if (!fullName.trim().matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
                    errors.add("Fullname chỉ được chứa số và chữ");
                }

                // validation department id
                Department department = new Department();
                if (Objects.isNull(depIdStr) || !depIdStr.trim().matches(StringCommon.NUMBER_REGEX)) {// kiểm tra có phải số hay ko
                    errors.add("DepartmentID không đúng định dạng");
                } else {
                    department = mapByDepId.get(Integer.parseInt(depIdStr));
                    if (Objects.isNull(department)) {
                        errors.add("DepartmentID không tồn tại");
                    }
                }

                // validation position id
                Position position = new Position();
                if (Objects.isNull(posIdStr) || !posIdStr.trim().matches(StringCommon.NUMBER_REGEX)) {// kiểm tra có phải số hay ko
                    errors.add("PositionID không đúng định dạng");
                } else {
                    position = mapByPosId.get(Integer.parseInt(posIdStr));
                    if (Objects.isNull(position)) {
                        errors.add("PositionID không tồn tại");
                    }
                }

                if (errors.isEmpty()) {// ko có lỗi
                    Account acc = new Account(username, fullName, email, department, position);
                    accountImports.add(acc);
                } else {
                    errorsRow.add(line + "," + String.join("-", errors));
                }
            }
            // thêm ds acc vào DB
            if (!accountImports.isEmpty()) {
                accountRepository.createAll(accountImports);
            }

            // xuất file lỗi
            if (!errorsRow.isEmpty()) {
                // địa chỉ xuất l
                String pathErr = "C:\\Users\\Admin\\Desktop\\rw102\\Java Core\\csv\\error_account.csv";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathErr))) {
                    bw.write(header +"," + "error_messages");
                    bw.newLine();
                    for (String error : errorsRow) {
                        bw.write(error);
                        bw.newLine();
                    }
                    bw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return String.format("Import thành công %d, import thất bại %d, %s"
                    , accountImports.size(), errorsRow.size(), !errorsRow.isEmpty() ?"Đã xuat ra file lỗi error_account.csv" :"");
        } catch (Exception e) {
            e.printStackTrace();
            return "Import thất bại";
        }
    }
}
