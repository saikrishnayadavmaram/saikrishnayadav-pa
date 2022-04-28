package streams;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Exit;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;


public class Streams {
    public static void main(String[] args) {

        //Setting Up the Properties
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "application");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //Using StreamsBuilder for Streams application toplogy
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //Setting up the input topic through which data is consumed
        KStream<String, String> kStreaminput = streamsBuilder.stream("IN");
        //Transforming the input value to UpperCase
        KStream<String, String> kStreamoutput = kStreaminput.mapValues(value -> value.toUpperCase());
        //Producing the Transformed Values To the Output Topic
        kStreamoutput.to("OUT", Produced.with(Serdes.String(), Serdes.String()));
        //Building the Streams Application
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), properties);
        //Starting the Application
        streams.start();
        //terminating the threads

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}

