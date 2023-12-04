import kotlin.math.pow

object Day04  {
    fun part1(inputs: List<String>) {
        inputs.map { input ->
            val numbers = input.substringAfter(": ")
            val (winningNumberString, cardNumberString) = numbers.split(" | ")
            val winningNumbers = winningNumberString.split(" ").mapNotNull {
                val trimmed = it.trim()
                if (trimmed.isNotBlank()) {
                    trimmed.toInt()
                } else null
            }
            val cardNumbers = cardNumberString.split(" ").run {
                mapNotNull {
                    val trimmed = it.trim()
                    if (trimmed.isNotBlank()) {
                        trimmed.toInt()
                    } else null
                }
            }
            val overlappingNumbers = winningNumbers.filter { cardNumbers.contains(it) }

            if (overlappingNumbers.isEmpty()) {
                0
            } else {
                2f.pow(overlappingNumbers.size - 1).toInt()
            }
        }.sum().println()
    }

    fun part2(inputs: List<String>) {
        val copies = MutableList(inputs.size) { 1 }
        inputs.asSequence().forEachIndexed { index, input ->
            val numbers = input.substringAfter(": ")
            val (winningNumberString, cardNumberString) = numbers.split(" | ")
            val winningNumbers = winningNumberString.split(" ").mapNotNull {
                val trimmed = it.trim()
                if (trimmed.isNotBlank()) {
                    trimmed.toInt()
                } else null
            }.toSet()
            val cardNumbers = cardNumberString.split(" ").run {
                mapNotNull {
                    val trimmed = it.trim()
                    if (trimmed.isNotBlank()) {
                        trimmed.toInt()
                    } else null
                }
            }.toSet()
            val overlappingNumbers = cardNumbers.filter { winningNumbers.contains(it) }

            if (overlappingNumbers.isNotEmpty()) {
                (index + 1 .. index + overlappingNumbers.size).forEach { affectedIndex ->
                    if (affectedIndex < copies.size) {
                        copies[affectedIndex] = copies[affectedIndex] + copies[index]
                    }
                }
            }
        }

        copies.sumOf { it }.println()
    }
}

fun main() {
    Day04.part1(readInput("Day04").sorted())
    Day04.part2(readInput("Day04").sorted())
}
