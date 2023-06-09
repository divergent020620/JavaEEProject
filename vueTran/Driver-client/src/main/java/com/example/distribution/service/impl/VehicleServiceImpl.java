package com.example.distribution.service.impl;

import com.example.distribution.model.entity.Vehicle;
import com.example.distribution.repository.VehicleRepository;
import com.example.distribution.service.VehicleService;
import com.example.distribution.utils.DataTimeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleRepository vehicleRepository;

    @CircuitBreaker(name = "vehicleService", fallbackMethod = "saveFallback")
    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicle.setCreateAt(DataTimeUtil.getNowTimeString());
        return vehicleRepository.save(vehicle);
    }
    public Vehicle saveFallback(Vehicle vehicle, Throwable ex) {
        // 在这里添加断路器打开后的备用逻辑
        // 返回一个默认的Vehicle对象，或者抛出异常等等
        return null;
    }
    @Override
    public void update(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

}
