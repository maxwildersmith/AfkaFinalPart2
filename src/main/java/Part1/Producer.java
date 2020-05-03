package Part1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;


public class Producer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("This program should be run with the Kafka topic name as an argument");
            System.exit(-1);
        }
        String topicName = args[0];
        Scanner in = new Scanner(System.in);
        System.out.println("Enter messages to send to the consumer, type 'exit' to quit:");
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        org.apache.kafka.clients.producer.Producer<String,String> producer = new KafkaProducer<String, String>(configProperties);
        String line;
         do{
             line = in.nextLine();
             ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName,line);
             producer.send(rec);
        }while(!line.trim().toLowerCase().equals("exit"));
        in.close();
        producer.close();
    }
}
