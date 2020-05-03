package Part1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;


public class Consumer {
    public static void main(String[] args)throws Exception{
        if (args.length != 2) {
            System.err.println("Run this program with the topic name and groupID as arguments");
            System.exit(-1);
        }
        String topicName = args[0];
        String groupId = args[1];

        ConsumerThread consumerRunnable = new ConsumerThread(topicName, groupId);
        consumerRunnable.start();
        consumerRunnable.join();
    }

    private static class ConsumerThread extends Thread{
        private final String topicName;
        private final String groupId;

        public ConsumerThread(String topicName, String groupId){
            this.topicName = topicName;
            this.groupId = groupId;
        }
        public void run() {
            Properties configProperties = new Properties();
            configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
            configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "Part1");

            //Figure out where to start processing messages from
            KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(configProperties);
            kafkaConsumer.subscribe(Collections.singletonList(topicName));
            //Start processing messages
            outer:
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                    if(record.value().equals("exit"))
                        break outer;
                    else
                        System.out.println(record.value());
            }

            kafkaConsumer.close();
            System.out.println("After closing KafkaConsumer");
        }
    }
}

