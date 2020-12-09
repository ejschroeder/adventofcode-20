package lol.schroeder

class HandheldHalting : AocPuzzle {
    override val filename = "/day8.txt"

    override fun solvePart1(lines: List<String>): Number {
        val instructions = lines.map { it.split(" ") }
            .map { Instruction(it[0], it[1].toInt()) }

        return ConsoleComputer(instructions).simulate()
    }

    override fun solvePart2(lines: List<String>): Number {
        val instructions = lines.map { it.split(" ") }
            .map { Instruction(it[0], it[1].toInt()) }

        instructions.forEachIndexed { index, instruction ->
            if (instruction.isFlippable) {
                val newInstructions = flipInstructionAtIndex(instructions, index)
                val computer = ConsoleComputer(newInstructions)
                val result = computer.simulate()
                if (computer.terminatedNormally) {
                    return result
                }
            }
        }

        return -1
    }

    private fun flipInstructionAtIndex(instructions: List<Instruction>, index: Int): MutableList<Instruction> {
        val newInstructions = instructions.toMutableList()
        newInstructions[index] = instructions[index].flip()
        return newInstructions
    }
}

data class Instruction(val operation: String, val arg: Int) {
    val isFlippable: Boolean
        get() = operation == "nop" || operation == "jmp"

    fun flip(): Instruction {
        if (!isFlippable) throw Exception("This instruction is not flippable!")
        val flippedOperation = if (operation == "nop") "jmp" else "nop"
        return copy(operation = flippedOperation)
    }
}

class ConsoleComputer(private val instructions: List<Instruction>) {
    private var instructionCounter = 0
    private var accumulator = 0
    private var priorInstructions = HashSet<Int>()
    val terminatedNormally: Boolean
        get() = instructionCounter >= instructions.size

    fun simulate(): Int {
        while(currentInstructionIsValid()) {
            val instruction = getNextInstruction()
            evaluate(instruction)
        }

        return accumulator
    }

    private fun getNextInstruction(): Instruction {
        priorInstructions.add(instructionCounter)
        return instructions[instructionCounter]
    }

    private fun currentInstructionIsValid() =
        instructionCounter < instructions.size && !priorInstructions.contains(instructionCounter)

    private fun evaluate(instruction: Instruction) {
        var nextInstructionIncrement = 1

        when (instruction.operation) {
            "nop" -> {}
            "acc" -> accumulator += instruction.arg
            "jmp" -> nextInstructionIncrement = instruction.arg
            else -> throw IllegalArgumentException("Instruction contains invalid operation '${instruction.operation}'")
        }

        instructionCounter += nextInstructionIncrement
    }


}
