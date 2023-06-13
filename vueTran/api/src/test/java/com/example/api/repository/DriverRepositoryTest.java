//package com.example.api.repository;
//
//import com.example.api.model.entity.Driver;
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
//public class DriverRepositoryTest {
//
//    @Autowired
//    private DriverRepository driverRepository;
//
//    private Driver driver;
//
//    @BeforeEach
//    public void setUp() {
//        // Create a new driver record
//        driver = new Driver();
//        driver.setId("1");
//        driver.setName("John Doe");
//        driver.setDriving(false);
//        driverRepository.save(driver);
//    }
//
//    @Test
//    public void testUpdateDriving() {
//        // Update the driving status of the driver record
//        driverRepository.updateDriving(true, driver.getId());
//
//        // Retrieve the updated driver record
//        Driver updatedDriver = driverRepository.findById(driver.getId()).orElse(null);
//
//        // Verify that the driving status has been updated
//        assertNotNull(updatedDriver);
//        assertTrue(updatedDriver.isDriving());
//    }
//
//    @Test
//    public void testFindAllByDriving() {
//        // Retrieve all driver records with a specific driving status
//        List<Driver> drivingDrivers = driverRepository.findAllByDriving(false);
//
//        // Verify that the list of driver records contains the test driver record
//        assertNotNull(drivingDrivers);
//        assertFalse(drivingDrivers.isEmpty());
//        assertTrue(drivingDrivers.contains(driver));
//    }
//}