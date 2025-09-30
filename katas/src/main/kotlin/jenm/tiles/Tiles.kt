package jenm.tiles

class Tiles {
    fun numTilePossibilities(tiles: String): Int {
        val chars = tiles.toCharArray() // char array

        // calculate subsets
        val subsets = subsets(chars)

        // for each subset, calculate permutations
        return subsets.flatMap {
            permutations(it)
        }.size
    }

    fun subsets(chars: CharArray): Set<List<Char>> {
        return chars.fold(listOf(emptyList<Char>())){ acc, c ->
            acc.flatMap {
                listOf(it.sorted(), (it + c).sorted())
            }
        }.filter { !it.isEmpty() }.toSet()
    }

    fun permutations(chars: List<Char>): List<List<Char>> {
        if(chars.size == 0) return emptyList()
        if(chars.size == 1) return listOf(listOf(chars[0]))

        return chars.indices.flatMap { n ->
            val perms = permutations(chars.filterIndexed { i,e -> i != n })
            // to each permutation, add chars[n] at the end or start
            perms.map {
                it + chars[n]
            }

        }
        // return listOf(chars)
    }

//    fun numTilePossibilities(tiles: String): Int {
//        val counts = tiles.groupingBy { it }.eachCount()
//
//        return countSequences(counts)
//    }
//
//    private fun countSequences(counts: Map<Char, Int>): Int {
//        return counts
//            .filter { it.value > 0 }
//            .map {
//                1 + countSequences(counts + (it.key to (it.value -1)))
//            }.sum()
//    }
}