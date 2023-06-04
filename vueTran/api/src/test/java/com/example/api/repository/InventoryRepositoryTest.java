//package com.example.api.repository;
//
//import com.example.api.model.entity.Inventory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class InventoryRepositoryTest {
//
//    @Autowired
//    private InventoryRepository inventoryRepository;
//
//    private Inventory inventory;
//
//    @BeforeEach
//    public void setUp() {
//        // Create a new inventory record
//        inventory = new Inventory();
//        inventory.setWid("1");
//        inventory.setCid("1");
//        inventory.setCount(123);
//        inventory.setName("abc");
//        inventoryRepository.save(inventory);
//    }
//
//    @Test
//    public void testFindByWidAndCid() {
//        // Retrieve the inventory record for a specific warehouse and product
//        Inventory foundInventory = inventoryRepository.findByWidAndCid("1", "1");
//
//        // Verify that the retrieved record matches the test inventory
//        assertNotNull(foundInventory);
//        assertEquals(inventory.getId(), foundInventory.getId());
//        assertEquals(inventory.getWid(), foundInventory.getWid());
//        assertEquals(inventory.getCid(), foundInventory.getCid());
//        assertEquals(inventory.getCount(), foundInventory.getCount());
//    }
//
//    @Test
//    public void testFindAllByCid() {
//        // Retrieve all inventory records for a specific product
//        List<Inventory> productInventory = inventoryRepository.findAllByCid("1");
//
//        // Verify that the list of inventory records contains the test inventory
//        assertNotNull(productInventory);
//        assertFalse(productInventory.isEmpty());
//        assertTrue(productInventory.contains(inventory));
//    }
//
//    @Test
//    public void testFindAllByWid() {
//        // Retrieve all inventory records for a specific warehouse
//        List<Inventory> warehouseInventory = inventoryRepository.findAllByWid("1");
//
//        // Verify that the list of inventory records contains the test inventory
//        assertNotNull(warehouseInventory);
//        assertFalse(warehouseInventory.isEmpty());
//        assertTrue(warehouseInventory.contains(inventory));
//    }
//}