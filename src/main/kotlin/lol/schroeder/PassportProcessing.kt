package lol.schroeder

class PassportProcessing : AocPuzzle {
    override val filename = "/day4.txt"

    private val validEyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    private val hairColorRegex = Regex("^#[0-9a-f]{6}$")
    private val heightRegex = Regex("^(\\d+)(cm|in)$")

    override fun solvePart1(lines: List<String>): Number {
        return createPassportMap(lines).count { hasRequiredFields(it) }
    }

    override fun solvePart2(lines: List<String>): Number {
        return createPassportMap(lines).count { hasRequiredFields(it) && hasValidValues(it) }
    }

    private fun createPassportMap(lines: List<String>): List<Map<String, String>> {
        return lines.joinToString(" ") { if (it == "") "|" else it }
            .split(" | ")
            .map(this::toPassportMap)
    }

    private fun hasRequiredFields(passport: Map<String, String>): Boolean {
        return passport.containsKey("byr") &&
                passport.containsKey("iyr") &&
                passport.containsKey("eyr") &&
                passport.containsKey("hgt") &&
                passport.containsKey("hcl") &&
                passport.containsKey("ecl") &&
                passport.containsKey("pid")
    }

    private fun hasValidValues(passport: Map<String, String>): Boolean {
        return validateBirthYear(passport["byr"]) &&
                validateIssueYear(passport["iyr"]) &&
                validateExpirationYear(passport["eyr"]) &&
                validateHeight(passport["hgt"]) &&
                validateHairColor(passport["hcl"]) &&
                validateEyeColor(passport["ecl"]) &&
                validatePassportId(passport["pid"])
    }

    private fun validateHeight(value: String?): Boolean {
        if (value == null) return false

        val matchResult = heightRegex.find(value) ?: return false

        val (height, unit) = matchResult.destructured

        return when (unit) {
            "in" -> height.toIntOrNull() in 59..76
            "cm" -> height.toIntOrNull() in 150..193
            else -> false
        }
    }

    private fun validateBirthYear(value: String?): Boolean {
        val year = value?.toIntOrNull()

        return if (year == null) {
            false
        } else {
            year in 1920..2002
        }
    }

    private fun validateIssueYear(value: String?): Boolean {
        val year = value?.toIntOrNull()

        return if (year == null) {
            false
        } else {
            year in 2010..2020
        }
    }

    private fun validateExpirationYear(value: String?): Boolean {
        val year = value?.toIntOrNull()

        return if (year == null) {
            false
        } else {
            year in 2020..2030
        }
    }

    private fun validateHairColor(value: String?): Boolean = hairColorRegex.matches(value ?: "")

    private fun validateEyeColor(value: String?): Boolean = validEyeColors.contains(value)

    private fun validatePassportId(value: String?): Boolean = value?.length == 9 && value.all { it.isDigit() }

    private fun toPassportMap(passportString: String): Map<String, String> = passportString.split(" ")
        .map { it.substringBefore(":") to it.substringAfter(":") }
        .toMap()

}