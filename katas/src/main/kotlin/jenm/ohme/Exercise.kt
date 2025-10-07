package jenm.ohme

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
 * [n,n,n,0]-> -1
 * [0,0,1,1]-> 2
 * [1,1,n,n] -> 0
 * [n,n,n,n]
 * ==============
 */
class Exercise {
    fun findSmallest1Index(matrix: List<List<Int>>): Int {
        var bestPositionFound = -1
        for(r in 0..matrix.lastIndex) { // 0,1,2,3
            val row = matrix.get(r)
            val startingIndex = if(bestPositionFound > 0) bestPositionFound-1 else row.lastIndex
            for(c in startingIndex downTo 0) {
                if(row[c] == 1){
                    bestPositionFound = c
                } else {
                    break
                }
            }

            if(bestPositionFound == 0) break
        }

        return bestPositionFound
    }
}