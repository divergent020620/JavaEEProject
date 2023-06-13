package com.example.commodity.controller;

import com.example.commodity.model.entity.Commodity;
import com.example.commodity.service.CommodityService;
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
@RequestMapping("/api/commodity")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_COMMODITY')")
public class CommodityController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private CommodityService commodityService;

    @PostMapping("")
    public Commodity save(@RequestBody Commodity commodity) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "commodity of name " + commodity.getName() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/commodity").build();
        Response response = client.newCall(request).execute();
        return commodityService.save(commodity);
    }

    @DeleteMapping("")
    public void delete(String id) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "commodity of id " + id + " deleted";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/commodity").build();
        Response response = client.newCall(request).execute();
        commodityService.delete(id);
    }

    @PutMapping("")
    public void update(@RequestBody Commodity commodity) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "commodity of id " + commodity.getId() + " updated";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/commodity").build();
        Response response = client.newCall(request).execute();
        commodityService.update(commodity);
    }

    @GetMapping("")
    public List<Commodity> findAll() throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "commodities listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/commodity").build();
        Response response = client.newCall(request).execute();
        return commodityService.findAll();
    }

    @GetMapping("/search/{name}")
    public List<Commodity> findByLikeName(@PathVariable String name) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "commodity of name " + name + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/commodity").build();
        Response response = client.newCall(request).execute();
        return commodityService.findAllByLikeName(name);
    }

    @GetMapping("/{id}")
    public Commodity findById(@PathVariable String id) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "commodity of id " + id + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/commodity").build();
        Response response = client.newCall(request).execute();
        return commodityService.findById(id);
    }

}
