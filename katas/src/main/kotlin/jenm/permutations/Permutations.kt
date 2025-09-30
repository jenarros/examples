package jenm.permutations

import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle

class Permutations {
    /**
     * nums does not contain duplicates
     */
    fun permute(nums: IntArray): Set<List<Int>> {
        if (nums.isEmpty()) return emptySet()
        if (nums.size == 1) return setOf(listOf(nums[0]))
        return nums.flatMap { num ->
            permute(nums.filter { it != num }.toIntArray()).map { it + num }
        }.toSet()
    }

    /*
     * nums may contain duplicates
     */
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        if (nums.size == 0) return emptyList()
        if (nums.size == 1) return listOf(listOf(nums[0]))

        // take each number x out of nums
        // calculate permutations of the remaining array
        // add x to each position of the returned arrays
        return nums.indices.flatMap { i ->
            val current = nums[i]
            val perms = permuteUnique(nums.filterIndexed { index, e -> index != i }.toIntArray())
            perms.flatMap {
                listOf(it + current)
            }
        }
    }
}