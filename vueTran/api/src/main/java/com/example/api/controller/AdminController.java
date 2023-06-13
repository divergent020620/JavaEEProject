package com.example.api.controller;

import com.example.api.model.dto.LoginDto;
import com.example.api.model.entity.Admin;
import com.example.api.model.enums.Role;
import com.example.api.repository.AdminRepository;
import com.example.api.service.AdminService;
import com.example.api.utils.JwtTokenUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Autowired
    private Tracer tracer;

    @Resource
    private AdminService adminService;

    @Resource
    private AdminRepository adminRepository;

    @GetMapping("hasInit")
    public boolean hasInit() {
        return adminRepository.existsAdminByRoles(Role.ROLE_SUPER_ADMIN.getValue());
    }

    @PostMapping("/init")
    public Admin init(@RequestBody Admin admin) throws Exception {
        if (adminRepository.existsAdminByRoles(Role.ROLE_SUPER_ADMIN.getValue())) {
            throw new Exception("初始化请求错误");
        }
        admin.setRoles(Role.ROLE_SUPER_ADMIN.getValue());
        return adminService.save(admin);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public List<Admin> findAll() throws IOException {
        String topic = "api-topic";
        String normalMessage = "admin listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-api-service/api").build();
        Response response = client.newCall(request).execute();
        return adminService.findAll();
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public void delete(String id) throws IOException {
        String topic = "api-topic";
        String normalMessage = "admin of id " + id + " deleted";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-api-service/api").build();
        Response response = client.newCall(request).execute();
        adminService.delete(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public Admin save(@RequestBody Admin admin) throws Exception {
        String topic = "api-topic";
        String normalMessage = "admin of id " + admin.getId() + " saved";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-api-service/api").build();
        Response response = client.newCall(request).execute();
        return adminService.save(admin);
    }

        @PostMapping("/login")
        public Map<String, Object> loginByEmail(String type, @RequestBody LoginDto dto) throws Exception {
            Map<String, Object> map = new HashMap<>();
            Admin admin = type.equals("email") ? adminService.loginByEmail(dto) : adminService.loginByPassword(dto);
            String token = adminService.createToken(admin,
                    dto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME);
            map.put("admin", admin);
            map.put("token", token);
            String topic = "api-topic";
            String normalMessage = dto.getEmail() + " logged in";
            kafkaTemplate.send(topic, normalMessage);
//            System.out.println(dto.getEmail());
            Request request = new Request.Builder().url("http://localhost:8082/cloud-api-service/api").build();
            Response response = client.newCall(request).execute();
            return map;
        }

    @GetMapping("/sendEmail")
    public void sendEmail(String email) throws Exception {
        Request request = new Request.Builder().url("http://localhost:8082/cloud-api-service/api").build();
        Response response = client.newCall(request).execute();
        adminService.sendEmail(email);
    }

}
