package jenm.examples.kafka

import jenm.examples.kafka.util.logMessage

fun main() {
    val app = System.getProperty("app", "unknown")
    logMessage("app=$app")

    when(app) {
        "producer" -> {
            val topic = System.getProperty("topic", "unknown")
            producer(topic)
        }
        "consumer" -> {
            val consumerGroup = System.getProperty("group", "unknown")
            val topic = System.getProperty("topics", "unknown")

            logMessage("topics=$topic")
            logMessage("consumerGroup=$consumerGroup")

            consumer(topic.split(",").toSet(), consumerGroup)
        }
         else -> throw RuntimeException("Unknown app name $app")
    }
}