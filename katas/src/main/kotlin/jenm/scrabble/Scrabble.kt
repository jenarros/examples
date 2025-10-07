package jenm.scrabble

import kotlin.collections.filter

class Scrabble(private val dict: Set<String>) {
    fun largestWordLadder1(word: String): List<String> {
        return largestWordLadder1(listOf(word))
    }

    fun largestWordLadder1(path: List<String>): List<String> {
        val word = path.last()
        val nextWords = dict.filter { it.length == word.length && !path.contains(it) && difference(it, word) == 1 }
        if (nextWords.isEmpty()) return path
        return nextWords.map {
            largestWordLadder1(path + it)
        }.maxBy { it.size }
    }

    private fun difference(word1: String, word2: String) =
        word1.indices.count { word1[it] != word2[it] }

    fun scoreOf(word: String): Int =
        word.map {
            when (it.uppercaseChar()) {
                'Q', 'Z' -> 10
                'J', 'X' -> 8
                'K' -> 5
                'B', 'C', 'M', 'P' -> 3
                'D', 'G' -> 2
                'A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U' -> 1
                else -> 0
            }
        }.sum()
}