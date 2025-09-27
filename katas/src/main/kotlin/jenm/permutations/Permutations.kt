package jenm.permutations

class Permutations {
    /*
     * 1,2,3
     * take 1 and calculate permutations of subset 2,3
     * then add 1 to all possible positions of all subsets
     * 2,3
     * take 2 and calculate permutations of subset 3
     * then add 1 to all possible positions of all subsets
     * > 2,3 -> 2,3 + 3,2
     * > 1,2,3 -> 1,2,3;2,1,3;2,3,1 + 1,3,2;3,1,2;3,2,1
    */
    fun permute(nums: IntArray): Set<List<Int>> {
        if(nums.size == 0) return emptySet()
        if(nums.size == 1) return setOf(listOf(nums[0]))

        val subsetPermutations = permute(nums.sliceArray(1..nums.size-1))

        return subsetPermutations.flatMap { subset ->
            (0..subset.size).map {
                val newList = subset.toMutableList()
                newList.add(it, nums[0])
                newList
            }
        }.toSet()
    }

    fun permute1(nums: IntArray): Set<List<Int>> {
        if(nums.isEmpty()) return emptySet()
        if(nums.size == 1) return setOf(listOf(nums[0]))
        return nums.flatMap { num ->
            permute1(nums.filter { it != num }.toIntArray()).map { it + num }
        }.toSet()
    }
}