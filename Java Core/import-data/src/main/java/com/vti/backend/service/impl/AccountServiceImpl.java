package com.vti.backend.service.impl;

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
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            List<Account> accounts = new ArrayList<>();
            header = br.readLine();//doc dòng đầu tiêu header (buổi sau dùng)
            // nếu line đọc ra có dữ liệu thfi sẽ phân tích dữ liệu để lấy ra cac thông tin
            while ((line = br.readLine()) != null) {
                String[] arrs = line.split(",");
                String email = arrs[0];
                String username = arrs[1];
                String fullName = arrs[2];
                Integer depId = Integer.parseInt(arrs[3]);
                Integer posId = Integer.parseInt(arrs[4]);
                // chuyen depId -> Department:   findById
                Department department = departmentRepository.findById(depId);

                // chuyen posId -> Position     findById
                Position position = positionRepository.findById(posId);

                Account acc = new Account(username, fullName, email, department, position);
                accounts.add(acc);
            }
            // thêm ds acc vào DB
            accountRepository.createAll(accounts);
            return "Import thành công";
        } catch (Exception e) {
            return "Import thất bại";
        }
   }
}
