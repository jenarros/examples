package jenm.examples.kafka

import jenm.examples.kafka.util.logMessage
import jenm.examples.kafka.util.writePortToTempFile
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName

fun kafka() {
    val kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))

    kafka.start()

    logMessage("Started kafka instance on " + kafka.bootstrapServers)

        .also {
            writePortToTempFile(kafka)
        }
    while (true) {
        Thread.sleep(1000)
    }
}