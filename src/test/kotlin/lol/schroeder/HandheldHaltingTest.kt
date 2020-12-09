package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HandheldHaltingTest {

    private val handheldHalting = HandheldHalting()
    private val input = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6"
    )
    
    @Test
    fun solvePart1() {
        assertThat(handheldHalting.solvePart1(input)).isEqualTo(5)
    }

    @Test
    fun solvePart2() {
        assertThat(handheldHalting.solvePart2(input)).isEqualTo(8)
    }
}