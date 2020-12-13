package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EncodingErrorTest {

    private val encodingError = EncodingError(5)
    private val input = listOf(
        "35",
        "20",
        "15",
        "25",
        "47",
        "40",
        "62",
        "55",
        "65",
        "95",
        "102",
        "117",
        "150",
        "182",
        "127",
        "219",
        "299",
        "277",
        "309",
        "576"
    )

    @Test
    fun solvePart1() {
        assertThat(encodingError.solvePart1(input)).isEqualTo(127)
    }

    @Test
    fun solvePart2() {
        assertThat(encodingError.solvePart2(input)).isEqualTo(62L)
    }
}