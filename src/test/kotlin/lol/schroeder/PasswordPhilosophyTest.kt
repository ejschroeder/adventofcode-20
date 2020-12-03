package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PasswordPhilosophyTest {

    private val passwordPhilosophy = PasswordPhilosophy()
    private val input = listOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc"
    )

    @Test
    fun solvePart1() {
        assertThat(passwordPhilosophy.solvePart1(input)).isEqualTo(2)
    }

    @Test
    fun solvePart2() {
        assertThat(passwordPhilosophy.solvePart2(input)).isEqualTo(1)
    }
}