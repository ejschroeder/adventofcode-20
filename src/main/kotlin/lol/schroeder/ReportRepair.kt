package lol.schroeder

import org.apache.commons.math3.util.CombinatoricsUtils


class ReportRepair : AocPuzzle {
    override val filename = "/day1.txt"

    override fun solvePart1(input: List<String>): Number {
        val nums = input.map(String::toLong)
        val nChoose2 = CombinatoricsUtils.combinationsIterator(nums.size, 2)
        return calculateExpenseEntryProduct(nums, nChoose2)
    }

    override fun solvePart2(input: List<String>): Number {
        val nums = input.map(String::toLong)
        val nChoose3 = CombinatoricsUtils.combinationsIterator(nums.size, 3)
        return calculateExpenseEntryProduct(nums, nChoose3)
    }

    private fun calculateExpenseEntryProduct(
        nums: List<Long>,
        combinationIterator: Iterator<IntArray>
    ) = combinationIterator.asSequence()
        .map { it.map(nums::get) }
        .find { it.sum() == 2020L }
        ?.fold(1L) { acc, num -> num * acc } ?: 0

}