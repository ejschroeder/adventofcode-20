package lol.schroeder

import kotlin.math.abs

class RainRisk : AocPuzzle {
    override val filename = "/day12.txt"

    override fun solvePart1(lines: List<String>): Number {
        val startingFerry = Ferry(Point(0, 0), Direction.E, Offset(0, 0))

        val ferry = lines.map { NavInstruction(it.first(), it.drop(1).toInt()) }
            .fold(startingFerry) { ferry, instruction -> executePart1Instruction(ferry, instruction) }

        return abs(ferry.point.x) + abs(ferry.point.y)
    }

    override fun solvePart2(lines: List<String>): Number {
        val startingFerry = Ferry(Point(0, 0), Direction.E, Offset(10, 1))

        val ferry = lines.map { NavInstruction(it.first(), it.drop(1).toInt()) }
            .fold(startingFerry) { ferry, instruction -> executePart2Instruction(ferry, instruction) }

        return abs(ferry.point.x) + abs(ferry.point.y)
    }

    private fun executePart1Instruction(ferry: Ferry, instruction: NavInstruction): Ferry {
        return when(instruction.action) {
            'N' -> ferry.copy(point = Point(ferry.point.x, ferry.point.y + instruction.value))
            'S' -> ferry.copy(point = Point(ferry.point.x, ferry.point.y - instruction.value))
            'E' -> ferry.copy(point = Point(ferry.point.x + instruction.value, ferry.point.y))
            'W' -> ferry.copy(point = Point(ferry.point.x - instruction.value, ferry.point.y))
            'L' -> ferry.copy(orientation = getNewOrientation(ferry, -instruction.value))
            'R' -> ferry.copy(orientation = getNewOrientation(ferry, instruction.value))
            'F' -> move(instruction.value, ferry)
            else -> ferry
        }
    }

    private fun executePart2Instruction(ferry: Ferry, instruction: NavInstruction): Ferry {
        return when(instruction.action) {
            'N' -> ferry.copy(waypoint = Offset(ferry.waypoint.dx, ferry.waypoint.dy + instruction.value))
            'S' -> ferry.copy(waypoint = Offset(ferry.waypoint.dx, ferry.waypoint.dy - instruction.value))
            'E' -> ferry.copy(waypoint = Offset(ferry.waypoint.dx + instruction.value, ferry.waypoint.dy))
            'W' -> ferry.copy(waypoint = Offset(ferry.waypoint.dx - instruction.value, ferry.waypoint.dy))
            'L' -> ferry.copy(waypoint = rotateWaypoint(ferry, -instruction.value))
            'R' -> ferry.copy(waypoint = rotateWaypoint(ferry, instruction.value))
            'F' -> moveToWaypointTimes(instruction.value, ferry)
            else -> ferry
        }
    }

    private fun moveToWaypointTimes(multiplier: Int, ferry: Ferry) =
        ferry.copy(point = Point(
            ferry.point.x + (ferry.waypoint.dx * multiplier),
            ferry.point.y + (ferry.waypoint.dy * multiplier)
        ))

    private fun rotateWaypoint(ferry: Ferry, degrees: Int): Offset {
        val rotationIncrement = degrees / 90
        return when ((rotationIncrement + 4).rem(4)) {
            1 -> Offset(ferry.waypoint.dy, -ferry.waypoint.dx)
            2 -> Offset(-ferry.waypoint.dx, -ferry.waypoint.dy)
            3 -> Offset(-ferry.waypoint.dy, ferry.waypoint.dx)
            else -> ferry.waypoint
        }
    }

    private fun getNewOrientation(ferry: Ferry, degrees: Int): Direction {
        val rotationIncrement = degrees / 90
        var newOrientationOrdinal = (ferry.orientation.ordinal + rotationIncrement).rem(4)
        if (newOrientationOrdinal < 0) newOrientationOrdinal += 4
        return Direction.values()[newOrientationOrdinal]
    }

    private fun move(units: Int, ferry: Ferry): Ferry {
        val dx = ferry.orientation.unitVector.dx * units
        val dy = ferry.orientation.unitVector.dy * units

        return ferry.copy(point = Point(ferry.point.x + dx, ferry.point.y + dy))
    }
}

data class NavInstruction(val action: Char, val value: Int)

data class Ferry(val point: Point, val orientation: Direction, val waypoint: Offset)

enum class Direction(val unitVector: Offset) {
    N(Offset(0, 1)),
    E(Offset(1, 0)),
    S(Offset(0, -1)),
    W(Offset(-1, 0))
}