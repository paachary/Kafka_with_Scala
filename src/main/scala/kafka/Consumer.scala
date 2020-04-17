package kafka

import java.util

import org.apache.kafka.clients.consumer._
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

/**
 * This class is responsible for
 * Consuming data from Kafka Topic with a set of required Kafka Configuration properties
 *
 */
class Consumer {

  val consumerGroupId = "first_application_group"

  /**
   * This function creates a kafka consumer instance
   * and with required set of kafka configuration properties,
   * reads data from kafka topic.
   * @param topic : A topic name of string datatype
   */
  def readFromKafka( topic : String) : Unit = {

    val log = LoggerFactory.getLogger(classOf[Consumer])

    val constants = new Constants
    val props = constants.props

    props.put(
      ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId)
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

    val consumer = new KafkaConsumer[String, String](props)

    consumer.subscribe(util.Arrays.asList(topic)) //multiple topics

    while (true) {
      val record = consumer.poll(1000).asScala
      for (data <- record.iterator) {
        println(data.key() + ":" +
          data.value() + ":" +
          data.offset() + ":" +
          data.partition())

        log.info(data.key() + ":" +
          data.value() + ":" +
          data.offset() + ":" +
          data.partition())
      }
    }
  }
}

/**
 * A companion object for the Kafka consumer class which
 * initiates the consumer
 */
object Consumer extends App {
  val logger = LoggerFactory.getLogger(Consumer.getClass+"_main")
  if (args.length > 0){
    val topic : String = args(0)
    val consumer = new Consumer
    consumer.readFromKafka(topic)
  }
  else
   logger.error("Please submit the necessary parameters to the class. "+
   " Usage >> " +
     " kafka.Consumer <topic name>"
   )
}
