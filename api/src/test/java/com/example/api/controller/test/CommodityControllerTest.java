package com.example.api.controller.test;


import com.example.api.model.entity.Commodity;
import com.example.api.service.CommodityService;
import com.example.api.service.impl.CommodityServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CommodityControllerTest {

    @Mock
    @Resource
    private CommodityServiceImpl commodityService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveCommodity() throws Exception {
        Commodity commodity = new Commodity();
//        commodityService=new CommodityServiceImpl();
        commodity.setId("1");
        commodity.setName("test");
        commodity.setPrice(1000.0);
        commodity.setCount(1);
        commodity.setDescription("hhh");
//        commodity.setCreateAt("2022-06-20");
        commodity.setUpdateAt("2022-06-20");
    }

    @Test
    public void testDeleteCommodity() throws Exception {
    }

    @Test
    public void testUpdateCommodity() throws Exception {
    }

    @Test
    public void testFindAllCommodity() throws Exception {
        List<Commodity> commodities = new ArrayList<>();
        Commodity commodity = new Commodity();
        commodity.setId("1");
        commodity.setName("test");
        commodities.add(commodity);
    }

    @Test
    public void testFindByLikeNameCommodity() throws Exception {
        List<Commodity> commodities = new ArrayList<>();
        Commodity commodity = new Commodity();
        commodity.setId("1");
        commodity.setName("test");
        commodities.add(commodity);
    }
}
