package jenm.examples.kafka

fun main() {
    val app = System.getProperty("app", "unknown")
    val topic = System.getProperty("topic", "unknown")
    println("app=$app")
    println("topic=$topic")

    when(app) {
        "producer" -> producer(topic)
        "consumer" -> consumer(topic)
         else -> throw RuntimeException("Unknown app name $app")
    }
}