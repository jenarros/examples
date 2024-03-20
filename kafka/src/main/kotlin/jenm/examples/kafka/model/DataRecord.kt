package jenm.examples.kafka.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jenm.examples.kafka.util.gson

data class DataRecord @JsonCreator constructor(
    @JsonProperty("message") val message: String,
    @JsonProperty("producedAt") val producedAt: String) {

    override fun toString(): String {
        return gson.toJson(this)
    }
}
