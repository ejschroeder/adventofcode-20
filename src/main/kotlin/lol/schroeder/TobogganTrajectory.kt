package lol.schroeder

class TobogganTrajectory : AocPuzzle {
    override val filename = "/day3.txt"

    override fun solvePart1(lines: List<String>): Number {
        return calculateTreeHits(lines, Slope(3, 1))
    }

    override fun solvePart2(lines: List<String>): Number {
        return listOf(
            Slope(1,1),
            Slope(3, 1),
            Slope(5, 1),
            Slope(7, 1),
            Slope(1,2)
        ).map { calculateTreeHits(lines, it) }.reduce { acc, i -> acc * i }
    }

    private fun calculateTreeHits(map: List<String>, slope: Slope): Int {
        return generateSequence(Point(0, 0)) { point ->
            point.nextFrom(slope).takeIf { it.x < map.size }
        }
            .map { map[it.x][it.y % map[it.x].length] }
            .count { it == '#' }
    }
}

data class Slope(val right: Int, val down: Int)
data class Point(val x: Int, val y: Int) {
    fun nextFrom(slope: Slope) = Point(x + slope.down, y + slope.right)
}
