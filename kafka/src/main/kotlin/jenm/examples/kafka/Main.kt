package jenm.examples.kafka

fun main(vararg args: String) {
    println("args=" + args.joinToString())
    val app = System.getProperty("app", "unknown")
    val topic = System.getProperty("topic", "unknown")
    println("app=$app")
    println("topic=$topic")

    when(app) {
        "kafka" -> kafka()
        "producer" -> producer(topic)
        "consumer" -> consumer(topic)
         else -> throw RuntimeException("Unknown app name $app")
    }
}