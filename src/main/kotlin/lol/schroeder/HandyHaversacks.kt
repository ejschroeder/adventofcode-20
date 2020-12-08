package lol.schroeder


class HandyHaversacks : AocPuzzle {
    override val filename = "/day7.txt"

    private val shinyGold = "shiny gold"
    private val bagColorRegex = Regex("^([\\w\\s]+) bags contain")
    private val childBagRegex = Regex("(\\d+) ([\\w\\s]+) bag")
    private var graph: Map<String, List<Bag>> = mapOf()

    override fun solvePart1(lines: List<String>): Number {
        graph = lines.associate { getBagColor(it) to getChildBags(it) }
        return graph.keys.count { canHoldShinyGoldBag(it) }
    }

    override fun solvePart2(lines: List<String>): Number {
        graph = lines.associate { getBagColor(it) to getChildBags(it) }
        return getShinyGoldContainedBags()
    }

    private fun getShinyGoldContainedBags(): Int {
        return graph[shinyGold]!!.sumBy { it.count * getChildBagCount(it.color) }
    }

    private fun getChildBagCount(color: String): Int {
        val bags = graph[color] ?: return 0
        return if (bags.isEmpty()) 1 else 1 + bags.sumBy { it.count * getChildBagCount(it.color) }
    }

    private fun canHoldShinyGoldBag(color: String): Boolean {
        val bags = graph[color] ?: return false

        if (bags.any { it.color == shinyGold }) {
            return true
        }

        return bags.any { canHoldShinyGoldBag(it.color) }
    }

    private fun getBagColor(line: String): String {
        val colorMatch = bagColorRegex.find(line)!!
        val (color) = colorMatch.destructured
        return color
    }

    private fun getChildBags(line: String): List<Bag> {
        return childBagRegex.findAll(line).map {
            val (number, color) = it.destructured
            Bag(color, number.toInt())
        }.toList()
    }
}

data class Bag(val color: String, val count: Int)