package jenm.examples.kafka.model

import jenm.examples.kafka.cloud.util.gson
import java.time.LocalDateTime

data class DataRecord(val count: Long, val producedAt: String = LocalDateTime.now().toString()) {
    override fun toString(): String {
        return gson.toJson(this)
    }
}
