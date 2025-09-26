package jenm.permutations

class Permutations {
    fun permute(nums: IntArray): Set<List<Int>> {
        if(nums.isEmpty()) return emptySet()
        if(nums.size == 1) return setOf(listOf(nums[0]))
        return nums.flatMap { num ->
            permute(nums.filter { it != num }.toIntArray()).map { it + num }
        }.toSet()
    }
}