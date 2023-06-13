package com.example.distribution.controller;

import com.example.distribution.model.entity.Vehicle;
import com.example.distribution.service.VehicleService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private VehicleService vehicleService;

    @PostMapping("")
    public Vehicle save(@RequestBody Vehicle vehicle) throws IOException {
        String topic = "driver-topic";
        String normalMessage = "vehicle of id " + vehicle.getId() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/vehicle").build();
        Response response = client.newCall(request).execute();
        return vehicleService.save(vehicle);
    }

    @GetMapping("")
    public List<Vehicle> findAll() throws IOException {
        String topic = "driver-topic";
        String normalMessage = "vehicles listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/vehicle").build();
        Response response = client.newCall(request).execute();
        return vehicleService.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable String id) throws IOException {
        String topic = "driver-topic";
        String normalMessage = "vehicle of id " + id + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/vehicle").build();
        Response response = client.newCall(request).execute();
        return vehicleService.findById(id);
    }

    @DeleteMapping("")
    public void delete(String id) throws IOException {
        String topic = "vehicle-topic";
        String normalMessage = "vehicle of id " + id + " deleted";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/vehicle").build();
        Response response = client.newCall(request).execute();
        vehicleService.delete(id);
    }

}
