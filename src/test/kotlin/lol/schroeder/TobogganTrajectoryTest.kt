package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TobogganTrajectoryTest {

    private var tobogganTrajectory = TobogganTrajectory()
    private val input = listOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#"
    )

    @Test
    fun solvePart1() {
        assertThat(tobogganTrajectory.solvePart1(input)).isEqualTo(7)
    }

    @Test
    fun solvePart2() {
        assertThat(tobogganTrajectory.solvePart2(input)).isEqualTo(336)
    }
}