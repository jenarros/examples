package jenm.scrabble

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertTimeout
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTimedValue

class ScrabbleTest {
    private val dict = setOf("cat", "bat", "mat", "prr", "par")
    private val scrabble = Scrabble(dict)

    val myTests = mapOf<String, (String) -> List<String>>(
        "largestWordLadder1" to scrabble::largestWordLadder1
    )

    @Test
    fun `can build word ladder`() = myTests.forEach { (name, func) ->
        // warm up
        (0..1000).forEach {
            assertEquals(listOf("cat", "bat", "mat"), func("cat"))
            assertEquals(listOf("par", "prr"), func("par"))
        }

        assertTimeout(Duration.ofMillis(10)) {
            (0..1000).map {
                assertEquals(listOf("cat", "bat", "mat"), func("cat"))
                assertEquals(listOf("par", "prr"), func("par"))
                it
            }
        }
    }

    @Test
    fun `can calculate scrabble score`() {
        assertEquals(5, scrabble.scoreOf("cat"))
    }
}