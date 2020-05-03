package Part2;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.Scanner;

public class Producer {
    public static void main(String[] argv) {
        if (argv.length != 1) {
            System.err.println("Make sure to run this program with a topic name as an argument");
            System.exit(-1);
        }
        String topicName = argv[0];
        Scanner in = new Scanner(System.in);

        System.out.println("Enter message (type exit to quit)");

        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        configProperties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,CountryPartitioner.class.getCanonicalName());
        configProperties.put("partitions.0","USA");
        configProperties.put("partitions.1","India");

        org.apache.kafka.clients.producer.Producer<String,String> producer = new KafkaProducer<String, String>(configProperties);
        String line = in.nextLine();
        while(!line.equals("exit")) {
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
            producer.send(rec, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("Message sent to topic ->" + metadata.topic()+ " ,partition->" + metadata.partition() +" stored at offset->" + metadata.offset());
                }
            });
            line = in.nextLine();
        }
        in.close();
        producer.close();
    }
}
