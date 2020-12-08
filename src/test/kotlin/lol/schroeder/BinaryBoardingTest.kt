package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BinaryBoardingTest {

    private val binaryBoarding = BinaryBoarding()
    private val input = listOf(
        "BFFFBBFRRR",
        "FFFBBBFRRR",
        "BBFFBBFRLL"
    )

    @Test
    fun solvePart1() {
        assertThat(binaryBoarding.solvePart1(input)).isEqualTo(820)
    }

    @Test
    fun solvePart2() {
    }
}