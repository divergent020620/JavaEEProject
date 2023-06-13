package com.example.commodity.controller;

import com.example.commodity.model.entity.Inventory;
import com.example.commodity.model.entity.InventoryRecord;
import com.example.commodity.model.vo.CommodityChartVo;
import com.example.commodity.service.InventoryRecordService;
import com.example.commodity.service.InventoryService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OkHttpClient client;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private InventoryRecordService recordService;

    @GetMapping("")
    public List<Inventory> findAll() throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "inventory listed";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return inventoryService.findAll();
    }

    @GetMapping("analyze")
    public List<CommodityChartVo> analyze(Integer type) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "tmp";
        switch (type) {
            case 1:
                normalMessage = "warehousing analyze initiated";
                break;
            case -1:
                normalMessage = "outbound analyze initiated";
                break;
        }
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return recordService.analyzeCommodity(type);
    }

    //指定仓库id
    //查询某个仓库的库存情况
    @GetMapping("/warehouse/{id}")
    public List<Inventory> findByWarehouse(@PathVariable String id) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "stock of warehouse of id " + id + " is found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return inventoryService.findByWarehouseId(id);
    }

    //指定商品id
    //查询某个商品在所有仓库的库存
    @GetMapping("/commodity/{id}")
    public List<Inventory> findByCommodity(@PathVariable String id) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "stock of commodity of id " + id + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return inventoryService.findByCommodityId(id);
    }

    //指定仓库id
    //查询某个仓库库内商品的出库入库记录
    @GetMapping("/record/warehouse/{id}")
    public List<InventoryRecord> findRecordByWarehouse(@PathVariable String id) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "records of warehouse of id " + id + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return recordService.findAllByWarehouseId(id);
    }

    //指定商品id
    //查询某个商品在所有仓库出库入库记录
    @GetMapping("/record/commodity/{id}")
    public List<InventoryRecord> findRecordByCommodity(@PathVariable String id) throws IOException {
        String topic = "commodity-topic";
        String normalMessage = "records of commodity of id " + id + " found";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return recordService.findAllByCommodityId(id);
    }

    @PostMapping("/in")
    public InventoryRecord in(@RequestBody InventoryRecord record) throws Exception {
        String topic = "commodity-topic";
        String normalMessage = "warehousing of commodity " + record.getName() + "done";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return recordService.in(record);
    }

    @PostMapping("/out")
    public InventoryRecord out(@RequestBody InventoryRecord record) throws Exception {
        String topic = "commodity-topic";
        String normalMessage = "outbound of commodity " + record.getName() + "done";
        kafkaTemplate.send(topic, normalMessage);
        Request request = new Request.Builder().url("http://localhost:8082/cloud-commodity-service/inventory").build();
        Response response = client.newCall(request).execute();
        return recordService.out(record);
    }


}
