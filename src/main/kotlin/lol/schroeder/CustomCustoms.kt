package lol.schroeder

class CustomCustoms : AocPuzzle {
    override val filename = "/day6.txt"

    override fun solvePart1(lines: List<String>): Number {
        return lines.joinToString("") { if (it == "") "|" else it }
            .split("|")
            .map { it.toSet() }
            .sumBy { it.size }
    }

    override fun solvePart2(lines: List<String>): Number {
        return lines.joinToString(" ") { if (it == "") "|" else it }
            .split(" | ")
            .map { it.split(" ")}
            .map { getUnanimousYesAnswersForGroup(it) }
            .sum()
    }

    private fun getUnanimousYesAnswersForGroup(group: List<String>): Int {
        val groupSize = group.size

        return group.fold(mutableMapOf<Char, Int>()) { acc, answers -> answers.groupingBy { it }.eachCountTo(acc) }
            .count { it.value == groupSize }
    }
}