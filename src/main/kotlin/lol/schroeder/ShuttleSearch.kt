package lol.schroeder

class ShuttleSearch : AocPuzzle {
    override val filename = "/day13.txt"

    override fun solvePart1(lines: List<String>): Number {
        val earliestDeparture = lines[0].toInt()
        val shortestWait = lines[1].split(",")
            .filter { it != "x" }
            .map { it.toInt() }
            .associateWith { it - earliestDeparture.rem(it) }
            .minByOrNull { it.value }

        return (shortestWait?.key ?: 0) * (shortestWait?.value ?: 0)
    }

    override fun solvePart2(lines: List<String>): Number {
        val ids = lines[1].split(",")
            .mapIndexed { idx, id -> Bus(id.toLongOrNull() ?: -1L, idx) }
            .filter { it.id != -1L }

        var step = 1L
        var t = 0L
        var idx = 0
        while (idx < ids.size) {
            t += step
            if ((t + ids[idx].index).rem(ids[idx].id) == 0L) {
                step *= ids[idx].id
                idx++
            }
        }
        return t
    }
}

data class Bus(val id: Long, val index: Int)