package com.example.distribution.service;

import com.example.distribution.model.entity.Distribution;

import java.util.List;

public interface DistributionService {

    Distribution save(Distribution distribution) throws Exception;

    List<Distribution> findAll();

}
