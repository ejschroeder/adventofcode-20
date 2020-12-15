package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DockingDataTest {

    private val dockingData = DockingData()
    private val input = listOf(
        "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
        "mem[8] = 11",
        "mem[7] = 101",
        "mem[8] = 0"
    )

    @Test
    fun solvePart1() {
        assertThat(dockingData.solvePart1(input)).isEqualTo(165L)
    }

    @Test
    fun solvePart2() {
    }
}