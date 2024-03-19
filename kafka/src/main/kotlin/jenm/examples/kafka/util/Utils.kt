package jenm.examples.kafka.cloud.util

import com.google.gson.Gson
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.errors.TopicExistsException
import org.testcontainers.containers.KafkaContainer
import java.io.FileOutputStream
import java.io.FileReader
import java.util.concurrent.ExecutionException


val gson = Gson()

fun loadKafkaConfig(): MutableMap<String, Any> =
    mutableMapOf("bootstrap.servers" to readPortFromTempFile())

fun writePortToTempFile(kafka: KafkaContainer) {
    FileOutputStream("/tmp/example-kafka-port").use {
        it.writer().use {
            it.append(kafka.bootstrapServers)
        }
    }
}

fun readPortFromTempFile(): String =
    FileReader("/tmp/example-kafka-port")
        .readLines().first()


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