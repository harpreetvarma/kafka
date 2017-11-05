package com.kafka.test;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Harpreet on 04-11-2017.
 */
public class SimpleKafkaProducer {
    private final String topic = "hello-topic";


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        org.apache.kafka.clients.producer.KafkaProducer producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);

        Scanner sc = new Scanner(System.in);

        String value = "";
        while (!value.equals("exit")) {
            value = sc.next();
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("hello-topic", value);
            try {
                producer.send(record);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("send msg successfully");
        }
    }
}
