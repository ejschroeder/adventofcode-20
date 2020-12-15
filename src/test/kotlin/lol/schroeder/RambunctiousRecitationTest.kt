package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RambunctiousRecitationTest {

    private val rambunctiousRecitation = RambunctiousRecitation()
    private val input = listOf("0,3,6")
    private val input1 = listOf("1,3,2")
    private val input2 = listOf("2,1,3")
    private val input3 = listOf("1,2,3")
    private val input4 = listOf("2,3,1")
    private val input5 = listOf("3,2,1")
    private val input6 = listOf("3,1,2")

    @Test
    fun solvePart1() {
        assertThat(rambunctiousRecitation.solvePart1(input)).isEqualTo(436)
        assertThat(rambunctiousRecitation.solvePart1(input1)).isEqualTo(1)
        assertThat(rambunctiousRecitation.solvePart1(input2)).isEqualTo(10)
        assertThat(rambunctiousRecitation.solvePart1(input3)).isEqualTo(27)
        assertThat(rambunctiousRecitation.solvePart1(input4)).isEqualTo(78)
        assertThat(rambunctiousRecitation.solvePart1(input5)).isEqualTo(438)
        assertThat(rambunctiousRecitation.solvePart1(input6)).isEqualTo(1836)
    }

    @Test
    fun solvePart2() {
        assertThat(rambunctiousRecitation.solvePart2(input)).isEqualTo(175594)
        assertThat(rambunctiousRecitation.solvePart2(input1)).isEqualTo(2578)
        assertThat(rambunctiousRecitation.solvePart2(input2)).isEqualTo(3544142)
        assertThat(rambunctiousRecitation.solvePart2(input3)).isEqualTo(261214)
        assertThat(rambunctiousRecitation.solvePart2(input4)).isEqualTo(6895259)
        assertThat(rambunctiousRecitation.solvePart2(input5)).isEqualTo(18)
        assertThat(rambunctiousRecitation.solvePart2(input6)).isEqualTo(362)
    }
}