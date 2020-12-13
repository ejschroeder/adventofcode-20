package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AdapterArrayTest {

    private val adapterArray = AdapterArray()
    private val input = listOf(
        "28",
        "33",
        "18",
        "42",
        "31",
        "14",
        "46",
        "20",
        "48",
        "47",
        "24",
        "23",
        "49",
        "45",
        "19",
        "38",
        "39",
        "11",
        "1",
        "32",
        "25",
        "35",
        "8",
        "17",
        "7",
        "9",
        "4",
        "2",
        "34",
        "10",
        "3"
    )

    @Test
    fun solvePart1() {
        assertThat(adapterArray.solvePart1(input)).isEqualTo(220)
    }

    @Test
    fun solvePart2() {
        assertThat(adapterArray.solvePart2(input)).isEqualTo(19208)
    }
}