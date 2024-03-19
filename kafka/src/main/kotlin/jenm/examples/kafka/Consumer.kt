package jenm.examples.kafka

import jenm.examples.kafka.model.DataRecord
import jenm.examples.kafka.cloud.util.loadKafkaConfig
import io.confluent.kafka.serializers.KafkaJsonDeserializer
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig.JSON_VALUE_TYPE
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration.ofMillis

fun consumer(topic: String) {
    // Load properties from disk.
    val props = loadKafkaConfig().also {
        // Add additional properties.
        it[KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        it[VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaJsonDeserializer::class.java.name
        it[JSON_VALUE_TYPE] = DataRecord::class.java
        it[GROUP_ID_CONFIG] = "kotlin_example_group_1"
        it[AUTO_OFFSET_RESET_CONFIG] = "earliest"
    }

    val consumer = KafkaConsumer<String, DataRecord>(props).apply {
        subscribe(listOf(topic))
    }

    var totalCount = 0L

    consumer.use {
        println("Started consumer for kafka instance at ${props["bootstrap.servers"]}")
        while (true) {
            totalCount = consumer
                .poll(ofMillis(100))
                .fold(totalCount) { accumulator, record ->
                    val newCount = accumulator + 1
                    println("Consumed record with key ${record.key()} and value ${record.value()}, and updated total count to $newCount")
                    newCount
                }
        }
    }
}

