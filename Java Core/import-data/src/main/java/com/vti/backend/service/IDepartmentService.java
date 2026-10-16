package com.vti.backend.service;


import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();

    String importCSV(String path);
}
