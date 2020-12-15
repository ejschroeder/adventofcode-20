package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RainRiskTest {

    private val rainRisk = RainRisk()
    private val input = listOf(
        "F10",
        "N3",
        "F7",
        "R90",
        "F11"
    )

    @Test
    fun solvePart1() {
        assertThat(rainRisk.solvePart1(input)).isEqualTo(25)
    }

    @Test
    fun solvePart2() {
        assertThat(rainRisk.solvePart2(input)).isEqualTo(286)
    }
}