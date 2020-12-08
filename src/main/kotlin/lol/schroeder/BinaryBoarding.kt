package lol.schroeder

import kotlin.math.pow

class BinaryBoarding : AocPuzzle {
    override val filename = "/day5.txt"

    override fun solvePart1(lines: List<String>): Number {
        return lines.map(calculateSeatId()).maxOrNull() ?: -1
    }

    override fun solvePart2(lines: List<String>): Number {
        val ids = lines.map(calculateSeatId())

        return findMissingNumber(ids)
    }

    private fun getValueFromBSP(string: String, startingRange: IntRange): Int {
        if (2.0.pow(string.length).toInt() != startingRange.count())
            throw Exception("Range does not support the length of string provided!")

        return string.fold(startingRange) { acc, c ->
            when (c) {
                'F', 'L' -> acc.first..(acc.last - acc.count() / 2)
                'B', 'R' -> (acc.first + acc.count() / 2)..acc.last
                else -> throw Exception("Something's wrong with your input here... [$string] '$c'")
            }
        }.first
    }

    private fun calculateSeatId(): (String) -> Int = {
        val row = getValueFromBSP(it.substring(0, 7), 0..127)
        val col = getValueFromBSP(it.substring(7), 0..7)
        (row * 8) + col
    }

    private fun findMissingNumber(numbers: List<Int>): Int {
        val min = numbers.minOrNull() ?: 0
        val nPlus1Sum = (numbers.size + 1) * (numbers.size + 2) / 2
        return numbers.fold(nPlus1Sum) { acc, num ->
            acc - (num - min + 1)
        } + min - 1
    }


}