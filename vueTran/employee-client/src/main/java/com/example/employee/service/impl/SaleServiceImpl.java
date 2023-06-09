package com.example.employee.service.impl;

import com.example.employee.model.entity.Sale;
import com.example.employee.repository.SaleRepository;
import com.example.employee.service.SaleService;
import com.example.employee.utils.DataTimeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleRepository saleRepository;

    @CircuitBreaker(name = "saleService", fallbackMethod = "saveFallback")
    @Override
    public Sale save(Sale sale) {
        sale.setCreateAt(DataTimeUtil.getNowTimeString());
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @CircuitBreaker(name = "saleService", fallbackMethod = "searchByCompanyFallback")
    @Override
    public List<Sale> searchByCompany(String name) {
        return saleRepository.findAllByCompanyLike(name);
    }

    public Sale saveFallback(Sale sale, Throwable ex) {
        // 在这里添加断路器打开后的备用逻辑
        // 返回一个默认的Sale对象，或者抛出异常等等
        return null;
    }

    public List<Sale> searchByCompanyFallback(String name, Throwable ex) {
        // 在这里添加断路器打开后的备用逻辑
        // 返回一个默认的Sale列表，或者抛出异常等等
        return null;
    }
}
