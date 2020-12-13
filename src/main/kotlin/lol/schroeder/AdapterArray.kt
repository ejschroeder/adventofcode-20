package lol.schroeder

class AdapterArray : AocPuzzle {
    override val filename = "/day10.txt"

    override fun solvePart1(lines: List<String>): Number {
        val adapters = mutableListOf(0)
        adapters.addAll(lines.map(String::toInt).sorted())

        val counts = adapters
            .windowed(2)
            .fold(Pair(0, 1)) { acc, window ->
                when (window[1] - window[0]) {
                    1 -> acc.copy(first = acc.first + 1)
                    3 -> acc.copy(second = acc.second + 1)
                    else -> acc
                }
            }

        return counts.first * counts.second
    }

    override fun solvePart2(lines: List<String>): Number {
        val adapters = mutableListOf(0)
        adapters.addAll(lines.map(String::toInt).sorted())
        val adjMap = adapters.associateWith { getCompatibleAdapters(it, adapters) }
        return Graph(adjMap).countUniquePaths(adapters.first(), adapters.last())
    }

    private fun getCompatibleAdapters(adapter: Int, adapters: List<Int>) = adapters.filter { it - adapter in 1..3 }
}

class Graph(private val adjacencyMap: Map<Int, List<Int>>) {
    private val cachedPathCounts = mutableMapOf<Int, Long>()

    fun countUniquePaths(start: Int, end: Int) = countUniquePaths(start, end, 0)

    private fun countUniquePaths(start: Int, end: Int, pathCount: Long): Long {
        return when (start) {
            end -> pathCount + 1
            in cachedPathCounts -> pathCount + cachedPathCounts[start]!!
            else -> {
                val currentPathCount = adjacencyMap[start]?.fold(0L) { acc, i -> countUniquePaths(i, end, acc) } ?: pathCount
                cachedPathCounts[start] = currentPathCount
                pathCount + currentPathCount
            }
        }
    }
}