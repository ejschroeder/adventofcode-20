package lol.schroeder

import org.apache.commons.math3.util.CombinatoricsUtils


class ReportRepair {

    fun solve(input: List<String>) {
        val nums = input.map(String::toLong)

        val nChoose2 = CombinatoricsUtils.combinationsIterator(nums.size, 2)
        val twoEntryProduct = calculateExpenseEntryProduct(nums, nChoose2)

        val nChoose3 = CombinatoricsUtils.combinationsIterator(nums.size, 3)
        val threeEntryProduct = calculateExpenseEntryProduct(nums, nChoose3)

        println("Two entry product: $twoEntryProduct")
        println("Three entry product: $threeEntryProduct")
    }

    private fun calculateExpenseEntryProduct(
        nums: List<Long>,
        combinationIterator: Iterator<IntArray>
    ) = combinationIterator.asSequence()
        .map { it.map(nums::get) }
        .find { it.sum() == 2020L }
        ?.fold(1L) { acc, num -> num * acc }

}