package com.example.api.repository;

import com.example.api.model.entity.Admin;
import com.example.api.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        // Create a new admin record
        admin = new Admin();
        admin.setId("1");
        admin.setEmail("johndoe@example.com");
        admin.setPassword("password");
        admin.setRoles("admin");
        adminRepository.save(admin);
    }

    @Test
    public void testFindAdminByEmailAndPassword() {
        // Retrieve the admin record by email and password
        Admin foundAdmin = adminRepository.findAdminByEmailAndPassword(admin.getEmail(), admin.getPassword());

        // Verify that the retrieved admin record matches the test admin record
        assertNotNull(foundAdmin);
        assertEquals(admin.getId(), foundAdmin.getId());
        assertEquals(admin.getEmail(), foundAdmin.getEmail());
        assertEquals(admin.getPassword(), foundAdmin.getPassword());
        assertEquals(admin.getRoles(), foundAdmin.getRoles());
    }

    @Test
    public void testFindAdminByEmail() {
        // Retrieve the admin record by email
        Admin foundAdmin = adminRepository.findAdminByEmail(admin.getEmail());

        // Verify that the retrieved admin record matches the test admin record
        assertNotNull(foundAdmin);
        assertEquals(admin.getId(), foundAdmin.getId());
        assertEquals(admin.getEmail(), foundAdmin.getEmail());
        assertEquals(admin.getPassword(), foundAdmin.getPassword());
        assertEquals(admin.getRoles(), foundAdmin.getRoles());
    }

    @Test
    public void testExistsAdminByRoles() {
        // Check if an admin record with specific roles exists
        boolean exists = adminRepository.existsAdminByRoles(admin.getRoles());

        // Verify that the result is true
        assertTrue(exists);
    }
}