package com.example.distribution.controller;

import com.example.distribution.model.entity.Distribution;
import com.example.distribution.repository.DriverRepository;
import com.example.distribution.repository.VehicleRepository;
import com.example.distribution.service.DistributionService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distribution")
public class DistributionController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private DistributionService distributionService;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    @PostMapping("")
    public Distribution save(@RequestBody Distribution distribution) throws Exception {
        String topic = "driver-topic";
        String normalMessage = "distribution of id " + distribution.getId() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/distribution").build();
        Response response = client.newCall(request).execute();
        return distributionService.save(distribution);
    }

    @GetMapping("")
    public List<Distribution> findAll() throws IOException {
        String topic = "driver-topic";
        String normalMessage = "distributions listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/distribution").build();
        Response response = client.newCall(request).execute();
        return distributionService.findAll();
    }

    @GetMapping("can")
    public Map<String, Object> can() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("drivers", driverRepository.findAll());
        map.put("vehicles", vehicleRepository.findAll());
        String topic = "driver-topic";
        String normalMessage = "available seeked";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/distribution").build();
        Response response = client.newCall(request).execute();
        return map;
    }

}
