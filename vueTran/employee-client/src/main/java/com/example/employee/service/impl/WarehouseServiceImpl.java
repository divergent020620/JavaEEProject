package com.example.employee.service.impl;

import com.example.employee.model.entity.Warehouse;
import com.example.employee.repository.WareHouseRepository;
import com.example.employee.service.WarehouseService;
import com.example.employee.utils.DataTimeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WareHouseRepository wareHouseRepository;

    @CircuitBreaker(name = "warehouseService", fallbackMethod = "saveFallback")
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
    @CircuitBreaker(name = "warehouseService", fallbackMethod = "deleteFallback")
    public void delete(String id) {
        wareHouseRepository.deleteById(id);
    }

    public Warehouse saveFallback(Warehouse warehouse, Throwable ex) {
        // 在这里添加断路器打开后的备用逻辑
        // 返回一个默认的Warehouse对象，或者抛出异常等等
        return null;
    }

    public void deleteFallback(String id, Throwable ex) {
        // 在这里添加断路器打开后的备用逻辑
        // 抛出异常或者进行日志记录等等
    }

}
