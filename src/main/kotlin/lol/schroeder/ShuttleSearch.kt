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

        val startingState = BusScheduleState(0, 1)

        return ids.fold(startingState) { state, bus ->
            val earliestTime = generateSequence(state.timestamp + state.stepSize) { it + state.stepSize }
                .first { (it + bus.index).rem(bus.id) == 0L }
            BusScheduleState(earliestTime, state.stepSize * bus.id)
        }.timestamp
    }
}

data class Bus(val id: Long, val index: Int)
data class BusScheduleState(val timestamp: Long, val stepSize: Long)