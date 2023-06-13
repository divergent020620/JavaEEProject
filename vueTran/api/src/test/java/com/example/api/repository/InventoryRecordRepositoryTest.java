//package com.example.api.repository;
//
//import com.example.api.model.entity.InventoryRecord;
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
//public class InventoryRecordRepositoryTest {
//
//    @Autowired
//    private InventoryRecordRepository inventoryRecordRepository;
//
//    private InventoryRecord inventoryRecord;
//
//    @BeforeEach
//    public void setUp() {
//        // Create a new inventory record
//        inventoryRecord = new InventoryRecord();
//        inventoryRecord.setWid("1");
//        inventoryRecord.setCid("1");
//        inventoryRecord.setType(1);
//        inventoryRecord.setWid("1");
//        inventoryRecordRepository.save(inventoryRecord);
//    }
//
//    @Test
//    public void testFindAllByWid() {
//        // Retrieve all inventory records for a specific warehouse
//        List<InventoryRecord> warehouseInventoryRecords = inventoryRecordRepository.findAllByWid("1");
//
//        // Verify that the list of inventory records contains the test inventory record
//        assertNotNull(warehouseInventoryRecords);
//        assertFalse(warehouseInventoryRecords.isEmpty());
//        assertTrue(warehouseInventoryRecords.contains(inventoryRecord));
//    }
//
//    @Test
//    public void testFindAllByType() {
//        // Retrieve all inventory records for a specific type
//        List<InventoryRecord> typeInventoryRecords = inventoryRecordRepository.findAllByType(1);
//
//        // Verify that the list of inventory records contains the test inventory record
//        assertNotNull(typeInventoryRecords);
//        assertFalse(typeInventoryRecords.isEmpty());
//        assertTrue(typeInventoryRecords.contains(inventoryRecord));
//    }
//
//    @Test
//    public void testFindAllByCid() {
//        // Retrieve all inventory records for a specific product
//        List<InventoryRecord> productInventoryRecords = inventoryRecordRepository.findAllByCid("1");
//
//        // Verify that the list of inventory records contains the test inventory record
//        assertNotNull(productInventoryRecords);
//        assertFalse(productInventoryRecords.isEmpty());
//        assertTrue(productInventoryRecords.contains(inventoryRecord));
//    }
//}