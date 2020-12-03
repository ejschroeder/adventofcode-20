package lol.schroeder

class PasswordPhilosophy : AocPuzzle {
    override val filename = "/day2.txt"

    private val formatRegex = Regex("^(\\d+)-(\\d+) ([a-z]): ([a-z]+)$")

    override fun solvePart1(lines: List<String>): Number {
        return lines
            .map(this::toPasswordEntry)
            .count(this::passwordMeetsCharacterCount)
    }

    override fun solvePart2(lines: List<String>): Number {
        return lines
            .map(this::toPasswordEntry)
            .count(this::passwordMeetsCharacterPosition)
    }

    private fun toPasswordEntry(it: String): PasswordEntry {
        val match = formatRegex.find(it)!!
        val (lowerBound, upperBound, character, password) = match.destructured
        return PasswordEntry(lowerBound.toInt(), upperBound.toInt(), character.first(), password)
    }

    private fun passwordMeetsCharacterCount(entry: PasswordEntry) =
        entry.run { password.count { it == requiredChar } in lowerBound..upperBound }

    private fun passwordMeetsCharacterPosition(entry: PasswordEntry) =
        entry.run { (password[lowerBound - 1] == requiredChar) xor (password[upperBound - 1] == requiredChar) }

}

data class PasswordEntry(val lowerBound: Int, val upperBound: Int, val requiredChar: Char, val password: String)