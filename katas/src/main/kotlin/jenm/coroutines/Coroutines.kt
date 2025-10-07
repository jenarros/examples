package jenm.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTimedValue

class Coroutines {
    fun doInSync() =
        runBlocking {
            // fire and forget
            launch {
                println("In coroutine scope: $coroutineContext")
            }
            val asyncs: List<Deferred<Int>> = (1..10).map {
                async {
                    randomInt()
                }
            }

            asyncs.awaitAll().sum()
        }

    fun randomInt() = (Math.random() * 100).toInt()
}

fun main(vararg args: String) {
    val coroutines = Coroutines()

    val result = measureTimedValue {
        coroutines.doInSync()
    }
    println("got ${result.value} in ${result.duration}");
}