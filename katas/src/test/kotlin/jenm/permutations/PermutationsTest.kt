package jenm.permutations

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PermutationsTest {

    @Test
    fun canCalculatePermutations() {
        val perms = Permutations()
        assertEquals(setOf(listOf(1)), perms.permute(intArrayOf(1)))
        assertEquals(setOf(listOf(0,1),listOf(1,0)), perms.permute(intArrayOf(0,1)))
    }
}