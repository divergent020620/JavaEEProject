package com.example.api.repository;

import com.example.api.model.entity.Commodity;
import com.example.api.repository.CommodityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CommodityRepositoryTest {

    @Autowired
    private CommodityRepository commodityRepository;

    @Test
    public void testFindByName() {
        // Create a new commodity
        Commodity commodity = new Commodity();
        commodity.setName("testCommodity");
        commodityRepository.save(commodity);

        // Find the commodity by name
        Commodity foundCommodity = commodityRepository.findByName("testCommodity");

        // Verify the commodity was found
        assertNotNull(foundCommodity);
        assertEquals(commodity.getName(), foundCommodity.getName());
    }

    @Test
    public void testFindByNameLike() {
        // Create a new commodity
        Commodity commodity = new Commodity();
        commodity.setName("testCommodity");
        commodityRepository.save(commodity);

        // Find the commodity by name using the "like" operator
        List<Commodity> foundCommodities = commodityRepository.findByNameLike("%test%");

        // Verify the commodity was found
        assertNotNull(foundCommodities);
        assertFalse(foundCommodities.isEmpty());
        assertEquals(commodity.getName(), foundCommodities.get(0).getName());
    }
}