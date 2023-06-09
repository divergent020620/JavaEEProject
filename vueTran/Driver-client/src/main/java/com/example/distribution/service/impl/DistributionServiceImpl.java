package com.example.distribution.service.impl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.example.distribution.model.entity.Distribution;
import com.example.distribution.model.entity.Driver;
import com.example.distribution.model.entity.Vehicle;
import com.example.distribution.repository.DistributionRepository;
import com.example.distribution.repository.DriverRepository;
import com.example.distribution.repository.VehicleRepository;
import com.example.distribution.service.DistributionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Resource
    private DistributionRepository distributionRepository;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    @CircuitBreaker(name = "distributionService", fallbackMethod = "saveFallback")
    @Override
    public Distribution save(Distribution distribution) throws Exception {
        if (distributionRepository.findById(distribution.getId()).isEmpty()) {
            Optional<Driver> driver = driverRepository.findById(distribution.getDid());
            Optional<Vehicle> vehicle = vehicleRepository.findById(distribution.getVid());
            if (driver.isEmpty() || vehicle.isEmpty()) {
                throw new Exception("请求参数错误");
            }
            if (driver.get().isDriving() || vehicle.get().isDriving()) {
                throw new Exception("司机或货车状态不可用");
            }
            driverRepository.updateDriving(true, distribution.getDid());
            vehicleRepository.updateDriving(true, distribution.getVid());
        }
        return distributionRepository.save(distribution);
    }

    public Distribution saveFallback(Distribution distribution, Throwable ex) {
        // 在这里添加断路器打开后的备用逻辑
        // 返回一个默认的Distribution对象，或者抛出异常等等
        return null;
    }
    @Override
    public List<Distribution> findAll() {
        return distributionRepository.findAll();
    }

}
