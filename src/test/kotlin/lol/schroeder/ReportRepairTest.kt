package lol.schroeder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ReportRepairTest {

    private var reportRepair = ReportRepair()
    private val input = listOf(
        "1721",
        "979",
        "366",
        "299",
        "675",
        "1456"
    )

    @Test
    fun solvePart1() {
        assertThat(reportRepair.solvePart1(input)).isEqualTo(514579L)
    }

    @Test
    fun solvePart2() {
        assertThat(reportRepair.solvePart2(input)).isEqualTo(241861950L)
    }
}