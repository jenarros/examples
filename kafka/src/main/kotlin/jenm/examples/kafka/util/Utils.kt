package jenm.examples.kafka.util

import com.google.gson.Gson
import org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.errors.TopicExistsException
import org.testcontainers.containers.KafkaContainer
import java.io.FileOutputStream
import java.io.FileReader
import java.util.concurrent.ExecutionException


val gson = Gson()

fun loadKafkaConfig(): MutableMap<String, Any> =
    mutableMapOf(BOOTSTRAP_SERVERS_CONFIG to "localhost:9094")
//    mutableMapOf(BOOTSTRAP_SERVERS_CONFIG to readPortFromTempFile())

fun writePortToTempFile(kafka: KafkaContainer) {
    FileOutputStream("/tmp/example-kafka-port").use {
        it.writer().use {
            it.append(kafka.bootstrapServers)
        }
    }
}

fun logMessage(message: String) = println(message)

fun createTopic(
    topic: String,
    partitions: Int,
    replication: Short,
    cloudConfig: MutableMap<String, Any>
) {
    val newTopic = NewTopic(topic, partitions, replication)

    try {
        with(AdminClient.create(cloudConfig)) {
            createTopics(listOf(newTopic)).all().get()
        }
    } catch (e: ExecutionException) {
        if (e.cause !is TopicExistsException) throw e
    }
}