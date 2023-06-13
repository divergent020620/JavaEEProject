//package com.example.api.consumer;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumer {
//
//    @KafkaListener(topics = {"api-topic"})
//    public void message1(ConsumerRecord<String, String> record){
//        // 消费的哪个topic、partition的消息,打印出消息内容
//        System.out.println("点对点消费1："+record.topic()+"-"+record.partition()+"-"+record.value());
//    }
//}
