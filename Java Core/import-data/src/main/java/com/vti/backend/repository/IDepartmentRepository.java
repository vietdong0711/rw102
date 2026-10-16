package com.vti.backend.repository;



import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    boolean create(Department department);

    void createAll(List<Department> departments);
}
