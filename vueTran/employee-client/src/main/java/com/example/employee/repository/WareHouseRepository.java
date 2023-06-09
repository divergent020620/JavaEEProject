package com.example.employee.repository;

import com.example.employee.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<Warehouse, String> {
}
