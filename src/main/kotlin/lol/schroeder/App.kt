package lol.schroeder

import java.io.File

fun main(args: Array<String>) {
    val puzzles = listOf(
        ReportRepair(),
        PasswordPhilosophy()
    )

    println("AoC 2020 Puzzle Solutions!")
    puzzles.forEachIndexed { idx, puzzle ->
        val inputLines = getFileLines(puzzle.filename)
        println("Day $idx")
        println("Part 1: ${puzzle.solvePart1(inputLines)}")
        println("Part 2: ${puzzle.solvePart2(inputLines)}")
        println()
    }
}

private fun getFileLines(filename: String): List<String> {
    val uri = object {}.javaClass.getResource(filename).toURI()
    return File(uri).readLines()
}

