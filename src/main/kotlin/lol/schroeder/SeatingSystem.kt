package lol.schroeder

class SeatingSystem : AocPuzzle {
    override val filename = "/day11.txt"
    private val offsets = listOf(
        Offset(-1, -1),
        Offset(-1,0),
        Offset(-1, 1),
        Offset(0, -1),
        Offset(0, 1),
        Offset(1, -1),
        Offset(1, 0),
        Offset(1, 1)
    )

    override fun solvePart1(lines: List<String>): Number {
        return generateSequence(lines) { getNextSeatState(it, this::countNearbyOccupiedSeats, this::getSeatStatePt1) }
            .last()
            .sumBy { it.count { seat -> seat == '#' } }
    }

    override fun solvePart2(lines: List<String>): Number {
        return generateSequence(lines) { getNextSeatState(it, this::countOccupiedSeatsInAllDirections, this::getSeatStatePt2) }
            .last()
            .sumBy { it.count { seat -> seat == '#' } }
    }

    private fun getSeatStatePt1(currentSeat: Char, occupiedCount: Int): Char {
        return if (currentSeat == 'L' && occupiedCount == 0) {
            '#'
        } else if (currentSeat == '#' && occupiedCount >= 4) {
            'L'
        } else {
            currentSeat
        }
    }

    private fun getSeatStatePt2(currentSeat: Char, occupiedCount: Int): Char {
        return if (currentSeat == 'L' && occupiedCount == 0) {
            '#'
        } else if (currentSeat == '#' && occupiedCount >= 5) {
            'L'
        } else {
            currentSeat
        }
    }


    private fun getNextSeatState(currentState: List<String>, getOccupiedCount: (Point, List<String>) -> Int, getNewSeatState :(Char, Int) -> Char): List<String>? {
        val nextState = currentState.toMutableList()
        for (i in currentState.indices) {
            for (j in currentState[i].indices) {
                val seatState = currentState[i][j]
                if (seatState != '.') {
                    val occupiedCount = getOccupiedCount(Point(i, j), currentState)
                    nextState[i] = nextState[i].replaceAt(j, getNewSeatState(seatState, occupiedCount))
                }
            }
        }

        return if (currentState == nextState) null else nextState
    }

    private fun hasOccupiedSeatInDirection(offset: Offset, point: Point, currentState: List<String>): Boolean {
        val nextPoint = point.nextFrom(offset).takeIf { isPointValid(it, currentState )}

        return generateSequence(nextPoint) { p -> p.nextFrom(offset).takeIf { isPointValid(it, currentState) } }
            .map { currentState[it.x][it.y] }
            .firstOrNull { it == 'L' || it == '#' } == '#'
    }

    private fun countOccupiedSeatsInAllDirections(point: Point, currentState: List<String>): Int {
        return offsets.count { hasOccupiedSeatInDirection(it, point, currentState) }
    }

    private fun isPointValid(point: Point, state: List<String>) =
        point.x in state.indices && point.y in state[point.x].indices

    private fun countNearbyOccupiedSeats(point: Point, currentState: List<String>): Int {
        return offsets.map { point.nextFrom(it) }
            .filter { isPointValid(it, currentState) }
            .count { currentState[it.x][it.y] == '#' }
    }
}

fun String.replaceAt(index: Int, character: Char): String = this.replaceRange(index, index + 1, character.toString())
