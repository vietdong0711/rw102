package com.vti.backend.controller;


import com.vti.backend.service.IPositionService;
import com.vti.backend.service.impl.PositonServiceImpl;
import com.vti.entity.Position;

import java.util.List;

public class PositionController {
    private IPositionService service;

    public PositionController() {
        this.service = new PositonServiceImpl();
    }

    public List<Position> findAll() {
        return service.findAll();
    }
}
