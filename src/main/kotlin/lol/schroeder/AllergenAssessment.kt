package lol.schroeder

class AllergenAssessment: AocPuzzle {
    override val filename = "/day21.txt"

    data class Food(val ingredients: Set<String>, val allergens: Set<String>)

    override fun solvePart1(lines: List<String>): Int {
        val foods = parseFoods(lines)

        val uniqueAllergens = foods.fold(setOf<String>()) { acc, food -> acc union food.allergens }
        val uniqueIngredients = foods.fold(setOf<String>()) { acc, food -> acc union food.ingredients }
        val foodsByAllergen = uniqueAllergens.associateWith { allergen -> foods.filter { it.allergens.contains(allergen) } }

        val sharedAllergenIngredients = foodsByAllergen
            .mapValues { (_, foods) -> foods.map { it.ingredients }.reduce { acc, ingredients -> acc intersect ingredients } }
            .map { it.value }
            .reduce { acc, ingredients -> acc union ingredients }

        val nonAllergens = uniqueIngredients.toMutableSet()
        nonAllergens.removeAll(sharedAllergenIngredients)

        return foods.sumOf { food -> food.ingredients.count { it in nonAllergens } }
    }

    override fun solvePart2(lines: List<String>): String {
        val foods = parseFoods(lines)

        val uniqueAllergens = foods.fold(setOf<String>()) { acc, food -> acc union food.allergens }

        var commonIngredients = uniqueAllergens.associateWith { allergen -> foods.filter { allergen in it.allergens } }
            .mapValues { entry -> entry.value.map { it.ingredients } }
            .mapValues { it.value.reduce { acc, ingredients -> acc intersect ingredients } }
            .mapValues { it.value.toList() }
            .toMutableMap()

        val solved = mutableMapOf<String, String>()

        while(commonIngredients.isNotEmpty()) {
            val solvedAllergens = commonIngredients.filterValues { it.size == 1 }.mapValues { it.value.first() }
            solvedAllergens.forEach {
                commonIngredients.remove(it.key)
                solved[it.value] = it.key
            }

            commonIngredients = commonIngredients
                .mapValues { it.value.filter { !solved.containsKey(it) } }
                .toMutableMap()
        }

        return solved.keys.sortedBy { solved[it] }.joinToString(",")
    }

    private fun parseFoods(lines: List<String>): List<Food> {
        val foods = lines.map { it.split(" (contains ") }
            .map {
                val ingredients = it.first().split(" ").toSet()
                val allergens = it.last().dropLast(1).split(", ").toSet()
                Food(ingredients, allergens)
            }
        return foods
    }
}