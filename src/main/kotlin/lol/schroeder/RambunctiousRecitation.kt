package lol.schroeder

class RambunctiousRecitation : AocPuzzle {
    override val filename = "/day15.txt"

    override fun solvePart1(lines: List<String>): Number {
        val startingNumbers = lines.first().split(",").map { it.toInt() }

        return reciteUntil(2020, startingNumbers)
    }

    override fun solvePart2(lines: List<String>): Number {
        val startingNumbers = lines.first().split(",").map { it.toInt() }

        return reciteUntil(30000000, startingNumbers)
    }

    private fun reciteUntil(turn: Int, startingNumbers: List<Int>): Int {
        val numberIndexes = mutableMapOf<Int, Int>()
        startingNumbers.dropLast(1).forEachIndexed { index, i -> numberIndexes[i] = index + 1 }

        var lastNum = startingNumbers.last()
        for (turn in (startingNumbers.size+1)..turn) {
            if (lastNum in numberIndexes) {
                val newNum = turn - 1 - numberIndexes[lastNum]!!
                numberIndexes[lastNum] = turn - 1
                lastNum = newNum
            } else {
                numberIndexes[lastNum] = turn - 1
                lastNum = 0
            }
        }

        return lastNum
    }

}