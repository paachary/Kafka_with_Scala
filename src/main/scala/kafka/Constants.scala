package kafka

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

/**
 * A helper class for storing all the configuration parameters
 * required by the kafka consumer and kafka producer
 */
class Constants {

  val props = new Properties()
  val bootStrapServers : String = "localhost:9092"

  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , bootStrapServers)
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , bootStrapServers)
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringDeserializer")
}
