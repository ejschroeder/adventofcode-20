package lol.schroeder

import java.io.File

fun main(args: Array<String>) {
    val reportRepair = ReportRepair()
    val uri = object {}.javaClass.getResource("/day1.txt").toURI()
    val lines = File(uri).readLines()
    reportRepair.solve(lines)
}

