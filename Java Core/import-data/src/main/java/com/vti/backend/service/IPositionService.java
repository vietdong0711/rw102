package com.vti.backend.service;


import com.vti.entity.Position;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();
}
