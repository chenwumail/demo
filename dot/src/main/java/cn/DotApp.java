package cn;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class DotApp
{
  String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

    private static Logger log = LoggerFactory.getLogger(DotApp.class);

    public static void main( String[] args ) throws InterruptedException {
//      SpringApplication.run(DotApp.class);
//      System.exit(0);
//        System.out.println( "Hello World!" );

//        produce(1);
//        Thread.sleep(1000);
//        consume(1);
//        consume2("zipkin", 0, 0, 2);
//        consume2("zipkin", 1, 0, 1);
//        consume2("zipkin", 2, 0, 1);
        if(args.length < 2) {
            System.out.println("-Dbootstrap.servers=localhost:9092 dot push <topic> <key> <value>");
            System.out.println("-Dbootstrap.servers=localhost:9092 dot peek <topic> [partition=0]" +
              " [offset=0] [number=1]");
            System.exit(-1);
        }

        String topic = args[1];
        if( "push".equals(args[0]) ) {
            String key = args[2];
            String value = args[3];
            produce2(topic, key, value);
        } else {
            int partition = args.length >= 3 ? Integer.parseInt(args[2]) : 0;
            long offset = args.length >= 4 ? Integer.parseInt(args[3]) : 0;
            int number = args.length >= 5 ? Integer.parseInt(args[4]) : 1;
            consume2(topic, partition, offset, number);
        }
    }

    public static void produce2(String topic, String key, String value) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", System.getProperty("bootstrap.servers", "localhost:9092"));;
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization" +
          ".StringSerializer");


        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            producer.send(new ProducerRecord<String, String>(topic, key,value));
            log.info("Sent: " + key + " = " + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
        log.info("Sent finished.");
    }

    public static void produce(int number) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", System.getProperty("bootstrap.servers", "localhost:9092"));
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.deserializer", System.getProperty("key.deserializer",
          "org.apache.kafka.common.serialization.StringDeserializer"));
        properties.put("value.deserializer", System.getProperty("value.deserializer",
          "org.apache.kafka.common.serialization.StringDeserializer"));
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < number; i++) {
                String msg = "Message " + i;
                Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>
                  ("HelloWorld", msg));
                RecordMetadata meta = future.get(1000, TimeUnit.SECONDS);
                log.info(String.format("%s-%s: offset: %d, message:%s", meta.topic(), meta
                  .partition(), meta.offset(), msg));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }
        log.info("finished.");

    }

    public static void consume(int number){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "hello-group");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("HelloWorld"));
        for(int i=0;i<number;i++) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            log.info("FETCHED: " + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic-partition-offset = %s-%d-%d, key = %s, value = %s",
                  record.topic(),
                  record.partition(),
                  record.offset(),
                  record.key(),
                  record.value());
                System.out.println();
            }
        }
        kafkaConsumer.close();

    }

    public static void consume2(String topic, int partition, long offset, int number){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", System.getProperty("bootstrap.servers", "localhost:9092"));
        properties.put("group.id", "pick-group-2");
        properties.put("enable.auto.commit", "false");
        properties.put("max.poll.records", 1);
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", System.getProperty("key.deserializer",
          "org.apache.kafka.common.serialization.StringDeserializer"));
        properties.put("value.deserializer", System.getProperty("value.deserializer",
          "org.apache.kafka.common.serialization.StringDeserializer"));

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        kafkaConsumer.assign(Arrays.asList(new TopicPartition(topic, partition)));
        kafkaConsumer.seek(new TopicPartition(topic, partition), offset);
        for(int i=0;i<number;i++) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(2000);
            log.info("FETCHED: " + records.count());

            for (ConsumerRecord<String, String> record : records) {
              Instant instant = Instant.ofEpochMilli(record.timestamp());
              ZoneId zone = ZoneId.of("UTC");
                log.info(String.format("timestamp = %s, offset = %d, key = %s, value = %s",
                  LocalDateTime.ofInstant(instant, zone),
                  record.offset(),record.key(), record.value()));
            }
        }
        kafkaConsumer.close();

    }
}
