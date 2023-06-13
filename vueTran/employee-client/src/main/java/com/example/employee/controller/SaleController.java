package com.example.employee.controller;

import com.example.employee.model.entity.Sale;
import com.example.employee.service.SaleService;
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
@RequestMapping("/api/sale")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_SALE')")
public class SaleController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private SaleService saleService;

    @PostMapping("")
    public Sale save(@RequestBody Sale sale) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "sale of id " + sale.getId() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/sale").build();
        Response response = client.newCall(request).execute();
        return saleService.save(sale);
    }

    @GetMapping("")
    public List<Sale> findAll() throws IOException {
        String topic = "employee-topic";
        String normalMessage = "sales listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/sale").build();
        Response response = client.newCall(request).execute();
        return saleService.findAll();
    }

    @GetMapping("/search/{name}")
    public List<Sale> search(@PathVariable String name) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "company of name " + name + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/sale").build();
        Response response = client.newCall(request).execute();
        return saleService.searchByCompany(name);
    }

}
