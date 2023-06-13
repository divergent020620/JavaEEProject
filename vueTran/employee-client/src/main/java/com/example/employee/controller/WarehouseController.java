package com.example.employee.controller;

import com.example.employee.model.entity.Warehouse;
import com.example.employee.service.WarehouseService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_WAREHOUSE')")
public class WarehouseController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private WarehouseService warehouseService;

    @PostMapping("")
    public Warehouse save(@RequestBody Warehouse warehouse) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "warehouse of name " + warehouse.getName() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/sale").build();
        Response response = client.newCall(request).execute();
        return warehouseService.save(warehouse);
    }

    @GetMapping("")
    public List<Warehouse> findAll() throws IOException {
        String topic = "employee-topic";
        String normalMessage = "warehouses listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/sale").build();
        Response response = client.newCall(request).execute();
        return warehouseService.findAll();
    }

    @DeleteMapping("")
    public void delete(String id) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "warehouse of id " + id + " deleted";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/sale").build();
        Response response = client.newCall(request).execute();
        warehouseService.delete(id);
    }

}
