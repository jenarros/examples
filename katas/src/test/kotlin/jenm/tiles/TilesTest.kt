package jenm.tiles

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TilesTest {

    @Test
    fun canCountTilePossibilities(){
        assertEquals(1, Tiles().numTilePossibilities("A"))
        assertEquals(8, Tiles().numTilePossibilities("AAB"))
        assertEquals(188, Tiles().numTilePossibilities("AAABBC"))
    }
}