package com.example.commodity.service.impl;

import com.example.commodity.model.entity.Inventory;
import com.example.commodity.repository.InventoryRepository;
import com.example.commodity.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findByCommodityId(String cid) {
        return inventoryRepository.findAllByCid(cid);
    }

    @Override
    public List<Inventory> findByWarehouseId(String wid) {
        return inventoryRepository.findAllByWid(wid);
    }

}
