package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SeatingSystemTest {

    private val seatingSystem = SeatingSystem()
    private val input = listOf(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL"
    )

    @Test
    fun solvePart1() {
        assertThat(seatingSystem.solvePart1(input)).isEqualTo(37)
    }

    @Test
    fun solvePart2() {
        assertThat(seatingSystem.solvePart2(input)).isEqualTo(26)
    }
}