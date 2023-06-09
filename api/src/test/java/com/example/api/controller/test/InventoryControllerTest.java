package com.example.api.controller.test;

import com.example.api.ApiApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@WebAppConfiguration
// @AutoConfigureMockMvc是用于自动配置MockMvc
@AutoConfigureMockMvc
public class InventoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void test() {
        // test方法中具体编写接口调用逻辑即可，主要是填入模拟接口入参调用。
    }
    @Test
    public void findByWarehouseTest () throws Exception {
        // 添加调用的接口路径
        MockHttpServletRequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/api/driver/warehouse/1");
        // 标识数据提交方式
        postRequestBuilder.contentType(MediaType.APPLICATION_JSON);
//        postRequestBuilder.param("1", String.valueOf(1));
        ResultActions resultActions = mockMvc.perform(postRequestBuilder);

        // 执行一个期望 在调用成功后，执行什么
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // 执行一般操作，输出调用结果
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取返回结果信息
        MvcResult response = resultActions.andReturn();

        System.out.println("result:"+response.getResponse().getContentAsString());

    }
    @Test
    public void findByCommodityTest () throws Exception {
        // 添加调用的接口路径
        MockHttpServletRequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/api/driver/record/warehouse/1");
        // 标识数据提交方式
        postRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        postRequestBuilder.param("1", String.valueOf(1));
        ResultActions resultActions = mockMvc.perform(postRequestBuilder);

        // 执行一个期望 在调用成功后，执行什么
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // 执行一般操作，输出调用结果
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取返回结果信息
        MvcResult response = resultActions.andReturn();

        System.out.println("result:"+response.getResponse().getContentAsString());

    }
    @Test
    public void findRecordByCommodityTest () throws Exception {
        // 添加调用的接口路径
        MockHttpServletRequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/api/driver//record/commodity/1");
        // 标识数据提交方式
        postRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        postRequestBuilder.param("1", String.valueOf(1));
        ResultActions resultActions = mockMvc.perform(postRequestBuilder);

        // 执行一个期望 在调用成功后，执行什么
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // 执行一般操作，输出调用结果
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取返回结果信息
        MvcResult response = resultActions.andReturn();

        System.out.println("result:"+response.getResponse().getContentAsString());

    }
    @Test
    public void findIdTest () throws Exception {
        // 添加调用的接口路径
        MockHttpServletRequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/api/driver/analyze");
        // 标识数据提交方式
        postRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        postRequestBuilder.param("1", String.valueOf(1));
        ResultActions resultActions = mockMvc.perform(postRequestBuilder);

        // 执行一个期望 在调用成功后，执行什么
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // 执行一般操作，输出调用结果
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取返回结果信息
        MvcResult response = resultActions.andReturn();

        System.out.println("result:"+response.getResponse().getContentAsString());

    }

    @Test

    public void findAllTest() throws Exception {
        // 添加调用的接口路径
        MockHttpServletRequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/api/inventory");
        // 标识数据提交方式
        postRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        // 参数
        postRequestBuilder.param("name", "张三");
        postRequestBuilder.param("age", "18");

        ResultActions resultActions = mockMvc.perform(postRequestBuilder);

        // 执行一个期望 在调用成功后，执行什么
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // 执行一般操作，输出调用结果
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取返回结果信息
        MvcResult response = resultActions.andReturn();

        System.out.println("result:" + response.getResponse().getContentAsString());

    }
}
