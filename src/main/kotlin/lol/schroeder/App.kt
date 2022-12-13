package lol.schroeder

import java.io.File
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val puzzles = listOf(
        ReportRepair(),
        PasswordPhilosophy(),
        TobogganTrajectory(),
        PassportProcessing(),
        BinaryBoarding(),
        CustomCustoms(),
        HandyHaversacks(),
        HandheldHalting(),
        EncodingError(25),
        AdapterArray(),
        SeatingSystem(),
        RainRisk(),
        ShuttleSearch(),
        DockingData(),
        RambunctiousRecitation(),
        AllergenAssessment(),
    )

    println("AoC 2020 Puzzle Solutions!")
    puzzles.forEachIndexed { idx, puzzle ->
        val inputLines = getFileLines(puzzle.filename)
        println("Day ${idx + 1} - ${puzzle.javaClass.simpleName}")
        var result: Any

        var nanoSeconds = measureNanoTime {
            result = puzzle.solvePart1(inputLines)
        }
        println("Part 1: $result - ${toMillis(nanoSeconds)} ms")

        nanoSeconds = measureNanoTime {
            result = puzzle.solvePart2(inputLines)
        }
        println("Part 2: $result - ${toMillis(nanoSeconds)} ms")
        println()
    }
}

private fun toMillis(nanoSeconds: Long) = nanoSeconds / 1_000_000.0

private fun getFileLines(filename: String): List<String> {
    val uri = object {}.javaClass.getResource(filename).toURI()
    return File(uri).readLines()
}

