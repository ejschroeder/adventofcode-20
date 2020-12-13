package lol.schroeder

import org.apache.commons.math3.util.CombinatoricsUtils

class EncodingError(private val preambleSize: Int) : AocPuzzle {
    override val filename = "/day9.txt"

    override fun solvePart1(lines: List<String>): Number {
        val numbers = lines.map(String::toLong)
        return findFirstInvalidNumber(numbers)
    }

    override fun solvePart2(lines: List<String>): Number {
        val numbers = lines.map(String::toLong)
        val targetSum = findFirstInvalidNumber(numbers)

        for (i in numbers.indices) {
            var currentSum = numbers[i]
            for (j in i + 1 until numbers.size) {
                currentSum += numbers[j]

                if (currentSum > targetSum) {
                    break
                }

                if (currentSum == targetSum) {
                    val contiguousNums = numbers.subList(i, j + 1)
                    val max = contiguousNums.maxOrNull() ?: 0
                    val min = contiguousNums.minOrNull() ?: 0
                    return max + min
                }
            }
        }
        return -1
    }

    private fun findFirstInvalidNumber(numbers: List<Long>): Long {
        val combinations = CombinatoricsUtils.combinationsIterator(preambleSize, 2).asSequence().toList()
        numbers.windowed(preambleSize).forEachIndexed { idx, window ->
            val number = numbers[idx + preambleSize]
            if (!combinations.map { window[it[0]] + window[it[1]] }.contains(number))
                return number
        }
        throw Exception("Could not find an invalid number")
    }
}