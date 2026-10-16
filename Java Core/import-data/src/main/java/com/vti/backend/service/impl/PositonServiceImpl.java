package com.vti.backend.service.impl;


import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.backend.service.IPositionService;
import com.vti.entity.Position;

import java.util.List;

public class PositonServiceImpl implements IPositionService {
    private IPositionRepository repository;

    public PositonServiceImpl() {
        this.repository = new PositionRepositoryImpl();
    }

    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }
}
