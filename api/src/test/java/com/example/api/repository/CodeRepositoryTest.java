package com.example.api.repository;

import com.example.api.model.entity.Code;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CodeRepositoryTest {



    @Autowired
    private CodeRepository codeRepository;

    private Code code;

    @BeforeEach
    public void setUp() {
        code = new Code("test@example.com", "123456");

    }

    @Test
    public void testFindByEmailAndValue() {
        Code foundCode = codeRepository.findByEmailAndValue("test@example.com", "123456");
        Assertions.assertEquals(foundCode, code);
    }

    @Test
    public void testFindByEmailAndValueNotFound() {
        Code foundCode = codeRepository.findByEmailAndValue("test@example.com", "wrong_value");
        Assertions.assertNull(foundCode);
    }

}