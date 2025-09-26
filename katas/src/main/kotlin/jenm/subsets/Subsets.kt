package jenm.subsets

class Subsets {
    fun subsetsWithDup(nums: IntArray): Set<List<Int>> {
        return nums.fold(listOf(emptyList<Int>())) { accSubsets, num ->
            accSubsets.flatMap { subset ->
                listOf(subset, subset + num)  // For each subset, create: original + with num added
            }
        }.map { it.sorted() }.toSet()
    }
}