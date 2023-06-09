package com.example.api.repository;

import com.example.api.model.entity.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    private Sale sale;

    @BeforeEach
    public void setUp() {
        // Create a new sale
        sale = new Sale();
        sale.setId("1");
        sale.setNumber("123");
        sale.setCompany("testCompany");
        sale.setCommodity("aa");
        sale.setCount("132");
        sale.setCreateAt("12");
        sale.setPay(true);
        saleRepository.save(sale);
    }

    @Test
    public void testFindAllByCompanyLike() {
        // Retrieve all sales for companies that include "test" in their name
        List<Sale> testCompanySales = saleRepository.findAllByCompanyLike("%test%");

        // Verify that the list of sales contains the test sale
        assertNotNull(testCompanySales);
        assertFalse(testCompanySales.isEmpty());
        assertTrue(testCompanySales.contains(sale));
    }
}