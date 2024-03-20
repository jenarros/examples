package jenm.examples.kafka

import jenm.examples.kafka.util.logMessage

fun main() {
    val app = System.getProperty("app", "unknown")
    logMessage("app=$app")

    when(app) {
        "producer" -> {
            val topic = System.getProperty("topic") ?: throw RuntimeException("topic needs to be specified")
            producer(topic)
        }
        "consumer" -> {
            val consumerGroup = System.getProperty("group") ?: throw RuntimeException("group needs to be specified")
            val topic = System.getProperty("topics") ?: throw RuntimeException("topic needs to be specified")

            logMessage("topics=$topic")
            logMessage("consumerGroup=$consumerGroup")

            consumer(topic.split(",").toSet(), consumerGroup)
        }
         else -> throw RuntimeException("Unknown app name $app")
    }
}