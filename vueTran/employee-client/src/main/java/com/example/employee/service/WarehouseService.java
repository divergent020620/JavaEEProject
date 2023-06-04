package com.example.employee.service;

import com.example.employee.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> findAll();

    void delete(String id);

}
