package jenm.ohme

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ExerciseTests {
    /**
     * Find the smallest column index
     * You are given a matrix of 1 and 0, dimensions n x m.
     * For every line the following is true:
     * 0s and 1s are sorted
     * If there are 0s in a row, they start on the left and "go" towards right.
     * If there are 1s in a row, they start on the  right and "go" towards left.
     * 0s and 1s don't mix, they have clear boundary if both 0s and 1s are in a row.
     * Task: find a column index where 1s has the smallest index.
     */
    /**
     * [0,0,0,0]
     * [0,0,1,1]
     * [0,1,1,1]
     * [1,1,1,1]
     * return 0 because row 3's last 1 is in column 0
     */
    private val exercise = Exercise()

    @Test
    fun `simpler cases`(){
        assertThat(-1, equalTo(exercise.findSmallest1Index(listOf(listOf(0)))))
        assertThat(0, equalTo(exercise.findSmallest1Index(listOf(listOf(1)))))
    }

    @Test
    fun `can find smallest index in last row`(){
        val matrix = listOf(
            listOf(0,0,0,0),
            listOf(0,0,1,1),
            listOf(0,1,1,1),
            listOf(1,1,1,1)
        )
        assertThat(exercise.findSmallest1Index(matrix), equalTo(0))
    }

    @Test
    fun `can find smallest index in middle row`(){
        val matrix = listOf(
            listOf(0,0,0,0),
            listOf(1,1,1,1),
            listOf(0,0,1,1),
            listOf(0,1,1,1)
        )
        assertThat(exercise.findSmallest1Index(matrix), equalTo(0))
    }

    @Test
    fun `finds smallest index in lowest row`(){
        val matrix = listOf(
            listOf(0,0,0,0),
            listOf(0,0,1,1),
            listOf(0,0,1,1),
            listOf(0,0,0,1)
        )
        assertThat(exercise.findSmallest1Index(matrix), equalTo(2))
    }
}