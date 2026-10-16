package com.vti.backend.controller;


import com.vti.backend.service.IDepartmentService;
import com.vti.backend.service.impl.DepartmentServiceImpl;
import com.vti.entity.Department;

import java.util.List;

public class DepartmentController {
    private IDepartmentService service;

    public DepartmentController() {
        this.service = new DepartmentServiceImpl();
    }

    public List<Department> findAll() {
        return service.findAll();
    }

    public String importCSV(String path) {
        return service.importCSV(path);
    }
}
