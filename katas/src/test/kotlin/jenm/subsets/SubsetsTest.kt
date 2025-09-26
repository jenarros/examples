package jenm.subsets

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SubsetsTest {
    @Test
    fun `can calculate subsets`() {
        val subsets = Subsets()
        assertEquals(setOf(emptyList(), listOf(1)), subsets.subsetsWithDup(intArrayOf(1)))
        assertEquals(setOf(emptyList(), listOf(1), listOf(1,2), listOf(1,2,2), listOf(2), listOf(2, 2)), subsets.subsetsWithDup(intArrayOf(1,2,2)))
    }
}