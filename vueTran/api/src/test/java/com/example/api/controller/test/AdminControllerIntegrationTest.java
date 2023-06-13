package com.example.api.controller.test;

import com.example.api.model.entity.Admin;
import com.example.api.model.enums.Role;
import com.example.api.repository.AdminRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testFindAll() {
        // 创建测试数据
        Admin admin1 = new Admin();
        admin1.setEmail("admin1@example.com");
        admin1.setPassword("admin1password");
        admin1.setRoles(Role.ROLE_ADMIN.getValue());
        adminRepository.save(admin1);

        Admin admin2 = new Admin();
        admin2.setEmail("admin2@example.com");
        admin2.setPassword("admin2password");
        admin2.setRoles(Role.ROLE_SUPER_ADMIN.getValue());
        adminRepository.save(admin2);

        // 发起请求并验证结果
        ResponseEntity<Admin> responseEntity = restTemplate.withBasicAuth("admin1@example.com", "admin1password")
                .getForEntity("/api/admin", Admin.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSave() {
        // 创建测试数据
        Admin admin = new Admin();
        admin.setEmail("newadmin@example.com");
        admin.setPassword("newadminpassword");
        admin.setRoles(Role.ROLE_ADMIN.getValue());

        // 发起请求并验证结果
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Admin> request = new HttpEntity<>(admin, headers);
        ResponseEntity<Map> responseEntity = restTemplate.withBasicAuth("admin1@example.com", "admin1password")
                .postForEntity("/api/admin", request, Map.class);
        Map<String, Object> response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
