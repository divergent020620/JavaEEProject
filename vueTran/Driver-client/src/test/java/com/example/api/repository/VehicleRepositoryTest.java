package com.example.api.repository;//package com.example.api.repository;
//
//import com.example.api.model.entity.Vehicle;
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
//public class VehicleRepositoryTest {
//
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    private Vehicle vehicle;
//
//    @BeforeEach
//    public void setUp() {
//        // Create a new vehicle
//        vehicle = new Vehicle();
//        vehicle.setId("1");
//        vehicle.setDriving(true);
//        vehicle.setType("123");
//        vehicle.setCreateAt("123");
//        vehicleRepository.save(vehicle);
//    }
//
//    @Test
//    public void testUpdateDriving() {
//        // Update the driving status of the vehicle
//        vehicleRepository.updateDriving(true, "1");
//
//        // Retrieve the vehicle from the database
//        Vehicle updatedVehicle = vehicleRepository.findById("1").orElse(null);
//
//        // Verify that the driving status was updated
//        assertNotNull(updatedVehicle);
//        assertTrue(updatedVehicle.isDriving());
//    }
//
//    @Test
//    public void testFindAllByDriving() {
//        // Retrieve all vehicles that are not currently driving
//        List<Vehicle> nonDrivingVehicles = vehicleRepository.findAllByDriving(false);
//
//        // Verify that the list of non-driving vehicles contains the test vehicle
//        assertNotNull(nonDrivingVehicles);
//        assertFalse(nonDrivingVehicles.isEmpty());
//        assertTrue(nonDrivingVehicles.contains(vehicle));
//    }
//}