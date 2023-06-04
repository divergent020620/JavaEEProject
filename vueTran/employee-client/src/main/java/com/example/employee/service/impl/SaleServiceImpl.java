package com.example.employee.service.impl;

import com.example.employee.model.entity.Sale;
import com.example.employee.repository.SaleRepository;
import com.example.employee.service.SaleService;
import com.example.employee.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleRepository saleRepository;

    @Override
    public Sale save(Sale sale) {
        sale.setCreateAt(DataTimeUtil.getNowTimeString());
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> searchByCompany(String name) {
        return saleRepository.findAllByCompanyLike(name);
    }

}
