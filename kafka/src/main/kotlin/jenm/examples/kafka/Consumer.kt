package jenm.examples.kafka

import io.confluent.kafka.serializers.KafkaJsonDeserializer
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig.JSON_VALUE_TYPE
import jenm.examples.kafka.model.DataRecord
import jenm.examples.kafka.util.loadKafkaConfig
import jenm.examples.kafka.util.logMessage
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration.ofMillis
import java.util.concurrent.atomic.AtomicLong

fun consumer(topic: String) {
    val props = loadKafkaConfig().also {
        // Add additional properties.
        it[KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        it[VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaJsonDeserializer::class.java.name
        it[JSON_VALUE_TYPE] = DataRecord::class.java
        it[GROUP_ID_CONFIG] = "kotlin_example_group_1"
        it[AUTO_OFFSET_RESET_CONFIG] = "earliest"
        it[RECONNECT_BACKOFF_MAX_MS_CONFIG] = 1000
        it[RECONNECT_BACKOFF_MS_CONFIG] = 100
    }

    val consumer = KafkaConsumer<String, DataRecord>(props)
        .apply {
            subscribe(listOf(topic))
        }

    val totalCount = AtomicLong(0)

    consumer.use {
        logMessage("Started consumer for kafka instance at ${props["bootstrap.servers"]}")
        while (true) {
            consumer
                .poll(ofMillis(100))
                .forEach { record ->
                    logMessage("Consumed record with key ${record.key()} and value ${record.value()}, and updated total count to ${totalCount.incrementAndGet()}")
                }
        }
    }
}

