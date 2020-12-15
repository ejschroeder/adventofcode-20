package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ShuttleSearchTest {

    private val shuttleSearch = ShuttleSearch()
    private val input = listOf(
        "939",
        "7,13,x,x,59,x,31,19"
    )

    @Test
    fun solvePart1() {
        assertThat(shuttleSearch.solvePart1(input)).isEqualTo(295)
    }

    @Test
    fun solvePart2() {
        assertThat(shuttleSearch.solvePart2(input)).isEqualTo(1068781L)
    }
}