package jenm.subsets

class Subsets {
    /**
     * 1,2,3
     * take 1, then calculate subsets of 2,3
     * for each subset returned, create one additional subset with 1 added
     * 2,3
     * > 2,3 + 2 + 3
     * >> 2,3 ; 2,3,1 + 2 ; 2,1 + 3 ; 3,1
     */
    fun subsetsWithDup(nums: IntArray): Set<List<Int>> {
        return nums.fold(listOf(emptyList<Int>())) { accSubsets, num ->
            accSubsets.flatMap { subset ->
                listOf(subset, subset + num)  // For each subset, create: original + with num added
            }
        }.map { it.sorted() }.toSet()
    }
}
