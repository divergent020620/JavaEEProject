package com.example.employee.service.impl;

import com.example.employee.model.entity.Warehouse;
import com.example.employee.repository.WareHouseRepository;
import com.example.employee.service.WarehouseService;
import com.example.employee.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WareHouseRepository wareHouseRepository;

    @Override
    public Warehouse save(Warehouse warehouse) {
        warehouse.setCreateAt(DataTimeUtil.getNowTimeString());
        return wareHouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        return wareHouseRepository.findAll();
    }

    @Override
    public void delete(String id) {
        wareHouseRepository.deleteById(id);
    }

}
