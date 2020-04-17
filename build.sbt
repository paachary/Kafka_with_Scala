name := "Kafka_with_Scala"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.1" exclude("log4j", "log4j") exclude("org.slf4j","slf4j-log4j12")

