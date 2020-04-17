package kafka

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
 * This class is responsible for
 * Consuming data to Kafka topic with a set of required Kafka Configuration properties
 */
class Producer {

  /***
   * This function creates a kafka producer instance
   * and with required set of kafka configuration properties,
   * puts data to kafka topic.
   * @param topic : Name of the kafka topic as a string
   */
  def writeToKafka( topic : String) : Unit = {
    val constants = new Constants

    val props = constants.props

    val producer = new KafkaProducer[String, String](props)

    for ( counter <- 1 until 10 ) {
      val record = new ProducerRecord[String, String] (topic, "key",
                                                  "value_" + Integer.toString(counter))
      producer.send(record)
    }
    producer.close()
  }
}

/**
 * A companion object for the Kafka producer class which
 * initiates the producer
 */
object Producer extends  App {
  val producer = new Producer
  producer.writeToKafka("new_topic")
}
