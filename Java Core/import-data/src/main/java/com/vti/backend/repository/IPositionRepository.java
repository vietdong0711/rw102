package com.vti.backend.repository;




import com.vti.entity.Position;

import java.util.List;

public interface IPositionRepository {
    List<Position> findAll();
}
