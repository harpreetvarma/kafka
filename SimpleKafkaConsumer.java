package com.kafka.test;

import com.esotericsoftware.minlog.Log;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import javax.jnlp.IntegrationService;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by Harpreet on 05-11-2017.
 */
public class SimpleKafkaConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // props.put("max.poll.records", "2");
Scanner sc = new Scanner(System.in);
        props.put("group.id", "hello-topic");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // consumer.subscribe(Collections.singletonList("hello-topic"));
        TopicPartition tp = new TopicPartition("hello-topic", 0);

        consumer.assign(Arrays.asList(tp));
        //  consumer.seek(tp, 29);
        // List<PartitionInfo> a = consumer.partitionsFor("hello-topic");

       /* List<PartitionInfo> a = consumer.partitionsFor("test");

        for (PartitionInfo i : a)
        {
            System.out.println(i);

        }*/
        //  System.out.println(a);

      /*  while (true)

        {
            ConsumerRecords<String, String> records = consumer.poll(100);


            for (ConsumerRecord<String, String> record : records) {
                System.out.println("******************************************************** \n" +
                        "TOPIC --- > " + record.topic() + "\n" +
                        "PARTITION --> " + record.partition() + "\n" +
                        "OFFSET --> " + record.offset() + "\n" +
                        "KEY -->" + record.key() + "\n" +
                        "VALUE --> " + record.value()
                        + "\n ********************************************************");
            }
            try {
                consumer.commitSync();
            } catch (CommitFailedException e) {
                Log.error("commit failed : " + e);
            }
        }*/

      int days = Integer.parseInt(sc.nextLine());

        // consumer.offsetsForTimes(tp,"", done->{} );
        System.out.println(consumer.offsetsForTimes(Collections.singletonMap(tp, (System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days)))).values());


        long offset = consumer.offsetsForTimes(Collections.singletonMap(tp, (System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days)))).get(tp).offset();
        consumer.seek(tp, offset);
        ConsumerRecords<String,String>  records= consumer.poll(100);

        System.out.println("| OFFSET | VALUE |");

        for (ConsumerRecord<String, String> record : records) {
            System.out.println("| "+record.offset()+ " | "+ record.value()+" |");

        }


     /* consumer.offsetsForTimes(tp, "600000", done -> {

          OffsetAndTimestamp ot = new OffsetAndTimestamp(0,600000);
          System.out.println("" + tp.partition() + "" + ot.);
            if (done.()) {
                OffsetAndTimestamp offsetAndTimestamp = done.result();
                System.out.println("Offset for topic=" + tp.getTopic() +
                        ", partition=" + tp.getPartition() + "\n" +
                        ", timestamp=" + timestamp + ", offset=" + offsetAndTimestamp.getOffset() +
                        ", offsetTimestamp=" + offsetAndTimestamp.getTimestamp());

            }
        });*/
    }


}
