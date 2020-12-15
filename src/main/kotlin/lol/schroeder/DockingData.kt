package lol.schroeder

class DockingData : AocPuzzle {
    override val filename = "/day14.txt"
    private val addressRegex = Regex("\\[(\\d+)]")

    override fun solvePart1(lines: List<String>): Number {
        val finalMemory = lines.map { it.split(" = ") }
            .fold(DockingComputerMemory()) { mem, instruction ->
                val (assignmentTarget, value) = instruction
                if (assignmentTarget == "mask") {
                    setNewMask(value, mem)
                } else {
                    val addressMatch = addressRegex.find(assignmentTarget)!!
                    val (address) = addressMatch.destructured
                    mem[address.toInt()] = value.toLong()
                    mem
                }
            }

        return finalMemory.memory.entries.fold(0L) { acc, entry -> acc + entry.value }
    }

    private fun setNewMask(mask: String, mem: DockingComputerMemory): DockingComputerMemory {
        var andMask = 0L.inv()
        var orMask = 0L

        mask.forEach {
            when (it) {
                '0' -> {
                    andMask = andMask shl 1
                    orMask = orMask shl 1
                }
                '1' -> {
                    andMask = (andMask shl 1) + 1
                    orMask = (orMask shl 1) + 1
                }
                else -> {
                    andMask = (andMask shl 1) + 1
                    orMask = orMask shl 1
                }
            }
        }

        return mem.copy(andMask = andMask, orMask = orMask)
    }

    override fun solvePart2(lines: List<String>): Number {
        return -1
    }


}

data class DockingComputerMemory(val andMask: Long = 0L.inv(), val orMask: Long = 0L, val memory: MutableMap<Int, Long> = mutableMapOf()) {
    operator fun set(address: Int, value: Long) {
        memory[address] = (value and andMask) or orMask
    }
}