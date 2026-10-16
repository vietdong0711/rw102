package com.vti.backend.service.impl;


import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.service.IDepartmentService;
import com.vti.entity.Department;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {
        this.repository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public String importCSV(String path) {
        // lay du lieu tu file theo đường dẫn ở path
        // kierm tra xem file nay có tồn tại ko,   đúng định dạng ko
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
            List<Department> departments = new ArrayList<>();
            header = br.readLine();//doc dòng đầu tiêu header (buổi sau dùng)
            // nếu line đọc ra có dữ liệu thfi sẽ phân tích dữ liệu để lấy ra cac thông tin
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] arrs = line.split(",");
                // chuyển dữ liệu trong file thành các dữ liệu liên quan đến department
                Department department = new Department(arrs[0]);
//                // gọi xuống repo để luu vao DB
//                repository.create(department);
                departments.add(department);
            }
            // thêm ds department vào DB
            repository.createAll(departments);

            return "Import thành công";
        } catch (Exception e) {
            return "Import thất bại";
        }
    }

}
