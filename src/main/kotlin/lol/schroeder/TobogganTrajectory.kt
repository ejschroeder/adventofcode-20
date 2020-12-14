package lol.schroeder

class TobogganTrajectory : AocPuzzle {
    override val filename = "/day3.txt"

    override fun solvePart1(lines: List<String>): Number {
        return calculateTreeHits(lines, Offset(3, 1))
    }

    override fun solvePart2(lines: List<String>): Number {
        return listOf(
            Offset(1,1),
            Offset(3, 1),
            Offset(5, 1),
            Offset(7, 1),
            Offset(1,2)
        ).map { calculateTreeHits(lines, it) }.reduce { acc, i -> acc * i }
    }

    private fun calculateTreeHits(map: List<String>, offset: Offset): Int {
        return generateSequence(Point(0, 0)) { point ->
            point.nextFrom(offset).takeIf { it.x < map.size }
        }
            .map { map[it.x][it.y % map[it.x].length] }
            .count { it == '#' }
    }
}


