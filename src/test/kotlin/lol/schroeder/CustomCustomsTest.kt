package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CustomCustomsTest {

    private val customCustoms = CustomCustoms()
    private val input = listOf(
        "abc",
        "",
        "a",
        "b",
        "c",
        "",
        "ab",
        "ac",
        "",
        "a",
        "a",
        "a",
        "a",
        "",
        "b"
    )

    @Test
    fun solvePart1() {
        assertThat(customCustoms.solvePart1(input)).isEqualTo(11)
    }

    @Test
    fun solvePart2() {
        assertThat(customCustoms.solvePart2(input)).isEqualTo(6)
    }
}