package cn.nanxiuzi.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyKafkaProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaDic.Kafka_ADDRESS_COLLECTION);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(props);
        for(int i=0;i<10000;i++){
            String messageContext=String.format("姓名%s,广东深圳%s,身高%s,体重%s,电话%s",Integer.toString(i),Integer.toString(i),Integer.toString(i),Integer.toString(i),Integer.toString(i));

            kafkaProducer.send(new ProducerRecord<String,String>(KafkaDic.PRODUCER_TOPIC,Integer.toString(i),messageContext));
            System.out.println("sented:"+messageContext);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        kafkaProducer.close();


    }
}
