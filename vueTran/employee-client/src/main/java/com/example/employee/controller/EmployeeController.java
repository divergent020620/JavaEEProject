package com.example.employee.controller;

import com.example.employee.model.entity.Employee;
import com.example.employee.service.EmployeeService;
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
@RequestMapping("/api/employee")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_EMPLOYEE')")
public class EmployeeController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> findAll() throws IOException {
        String topic = "employee-topic";
        String normalMessage = "employee listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/employee").build();
        Response response = client.newCall(request).execute();
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable String id) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "employee of id " + id + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/employee").build();
        Response response = client.newCall(request).execute();
        return employeeService.findById(id);
    }

    @PostMapping("")
    public Employee save(@RequestBody Employee employee) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "employee of id " + employee.getId() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/employee").build();
        Response response = client.newCall(request).execute();
        return employeeService.save(employee);
    }

    @PutMapping("")
    public void update(@RequestBody Employee employee) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "employee of id " + employee.getId() + " updated";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/employee").build();
        Response response = client.newCall(request).execute();
        employeeService.update(employee);
    }

    @DeleteMapping("")
    public void delete(String id) throws IOException {
        String topic = "employee-topic";
        String normalMessage = "employee of id " + id + " deleted";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-driver-service/employee").build();
        Response response = client.newCall(request).execute();
        employeeService.delete(id);
    }

}
