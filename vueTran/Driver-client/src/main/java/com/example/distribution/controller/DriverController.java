package com.example.distribution.controller;

import com.example.distribution.model.entity.Driver;
import com.example.distribution.service.DriverService;
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
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private DriverService driverService;

    @PostMapping("")
    public Driver save(@RequestBody Driver driver) throws IOException {
        String topic = "driver-topic";
        String normalMessage = "driver of id " + driver.getId() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/driver").build();
        Response response = client.newCall(request).execute();
        return driverService.save(driver);
    }

    @GetMapping("")
    public List<Driver> findAll() throws IOException {
        String topic = "driver-topic";
        String normalMessage = "drivers listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/driver").build();
        Response response = client.newCall(request).execute();
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public Driver findById(@PathVariable String id) throws IOException {
        String topic = "driver-topic";
        String normalMessage = "driver of id " + id + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/driver").build();
        Response response = client.newCall(request).execute();
        return driverService.findById(id);
    }

    @DeleteMapping("")
    public void delete(String id) throws IOException {
        String topic = "driver-topic";
        String normalMessage = "driver of id " + id + " deleted";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/driver").build();
        Response response = client.newCall(request).execute();
        driverService.delete(id);
    }

}
