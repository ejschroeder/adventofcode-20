package lol.schroeder

interface AocPuzzle {
    val filename: String

    fun solvePart1(lines: List<String>): Any
    fun solvePart2(lines: List<String>): Any
}