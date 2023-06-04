package com.example.employee.service;

import com.example.employee.model.entity.Sale;

import java.util.List;

public interface SaleService {

    Sale save(Sale sale);

    List<Sale> findAll();

    List<Sale> searchByCompany(String name);

}
