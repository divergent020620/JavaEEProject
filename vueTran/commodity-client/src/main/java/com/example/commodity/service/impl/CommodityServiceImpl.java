package com.example.commodity.service.impl;

import com.example.commodity.model.entity.Commodity;
import com.example.commodity.repository.CommodityRepository;
import com.example.commodity.service.CommodityService;
import com.example.commodity.utils.DataTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private CommodityRepository commodityRepository;

    @Override
    public Commodity save(Commodity commodity) {
        commodity.setCreateAt(DataTimeUtil.getNowTimeString());
        return commodityRepository.save(commodity);
    }

    @Override
    public void update(Commodity commodity) {
        commodity.setUpdateAt(DataTimeUtil.getNowTimeString());
//        String topic = "commodity-topic";
//        String normalMessage = "update commodity";
//        kafkaTemplate.send(topic, normalMessage);
//        System.out.println("fuck u");
        commodityRepository.save(commodity);
    }

    @Override
    public void delete(String id) {
        commodityRepository.deleteById(id);
    }

    @Override
    public Commodity findById(String id) {
        return commodityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commodity> findAll() {
        return commodityRepository.findAll();
    }

    @Override
    public List<Commodity> findAllByLikeName(String name) {
        return commodityRepository.findByNameLike("%" + name + "%");
    }

}
