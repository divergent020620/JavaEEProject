package com.example.distribution.repository;

import com.example.distribution.model.entity.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, String> {
}
