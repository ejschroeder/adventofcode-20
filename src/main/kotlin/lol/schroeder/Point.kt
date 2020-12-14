package lol.schroeder

data class Offset(val dx: Int, val dy: Int)
data class Point(val x: Int, val y: Int) {
    fun nextFrom(offset: Offset) = Point(x + offset.dy, y + offset.dx)
}