package kafka

import java.util

import org.apache.kafka.clients.consumer._
import org.apache.kafka.common.TopicPartition

import scala.collection.JavaConverters._

/**
 * This class is responsible for
 * Consuming data from a particular Kafka Topic partition and an offset
 * with a set of required Kafka Configuration properties
 */
class ConsumerAssignSeek {

  /**
   *  This function creates a kafka consumer instance
   *  and with required set of kafka configuration properties,
   *  reads data from kafka topic partition and an offset, using the assign and seek methods.
   * @param topic : A topic name of string datatype
   */
  def readFromKafka( topic : String) : Unit = {

    val constants = new Constants
    val props = constants.props

    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    val consumer = new KafkaConsumer[String, String](props)

    val partitionNo : Int = 1

    val topicPartition : TopicPartition = new TopicPartition(topic, partitionNo)
    consumer.assign(util.Arrays.asList(topicPartition))

    val offset : Long = 15L
    consumer.seek(topicPartition, offset)

    while(true) {
      val record = consumer.poll(1000).asScala
    }
  }
}

/**
 * A companion object for the Kafka consumer class which
 * initiates the consumer
 */
object  ConsumerAssignSeek extends App {
  val consumerAssignSeek = new ConsumerAssignSeek
  consumerAssignSeek.readFromKafka("first_topic")
}