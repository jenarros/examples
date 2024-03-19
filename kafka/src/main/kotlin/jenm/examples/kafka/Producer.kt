package jenm.examples.kafka

import io.confluent.kafka.serializers.KafkaJsonSerializer
import jenm.examples.kafka.cloud.util.createTopic
import jenm.examples.kafka.model.DataRecord
import jenm.examples.kafka.cloud.util.loadKafkaConfig
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.errors.TopicExistsException
import org.apache.kafka.common.serialization.StringSerializer
import java.util.concurrent.ExecutionException

fun producer(topic: String) {
    val kafkaProps = loadKafkaConfig()

    // Create topic if needed
    createTopic(topic, 1, 1, kafkaProps)

    // Add additional properties.
    kafkaProps[ACKS_CONFIG] = "all"
    kafkaProps[KEY_SERIALIZER_CLASS_CONFIG] = "${StringSerializer::class.qualifiedName}"
    kafkaProps[VALUE_SERIALIZER_CLASS_CONFIG] = "${KafkaJsonSerializer::class.qualifiedName}"

    // Produce sample data
    val numMessages = 10
    KafkaProducer<String, DataRecord>(kafkaProps).use { producer ->
        repeat(numMessages) { i ->
            val key = "message" // key is used by kafka to maintain order and it is optional
            val record = DataRecord((i+1).toLong())

            producer.send(ProducerRecord(topic, key, record)) { m: RecordMetadata, e: Exception? ->
                when (e) {
                    // no exception, good to go!
                    null -> println("Produced record $record to topic ${m.topic()} partition [${m.partition()}] @ offset ${m.offset()}")
                    // print stacktrace in case of exception
                    else -> e.printStackTrace()
                }
            }
        }

        producer.flush()
        println("$numMessages messages were produced to topic $topic")
    }

}


