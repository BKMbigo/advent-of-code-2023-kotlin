object Day01 {

    fun part1(input: List<String>) {
        input.sumOf {
            val first = it.first { it.isDigit() }.digitToInt()
            val last = it.last { it.isDigit() }.digitToInt()

            buildString {
                append(first)
                append(last)
            }.toInt()
        }.println()
    }

    fun part2(input: List<String>) {
        val firstNumberRegex = """.*?(one|two|three|four|five|six|seven|eight|nine|\d).*""".toRegex()
        val lastNumberRegex = """.+(one|two|three|four|five|six|seven|eight|nine|\d).*?$""".toRegex()

        val conversionMap = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        input.sumOf {
            val (first) = firstNumberRegex.find(it)!!.destructured
            val last = lastNumberRegex.find(it)?.destructured?.component1()

            val firstNumber = if (first.length != 1) {
                conversionMap[first]
            } else {
                first.toInt()
            }
            val lastNumber = if (last?.length != 1) {
                conversionMap[last]
            }else {
                last.toInt()
            }

            buildString {
                append(firstNumber)
                if(lastNumber!= null) {
                    append(lastNumber)
                } else {
                    append(firstNumber)
                }
            }.toInt()
        }.println()
    }

}

fun main() {
    val dayInput = readInput("Day01")

    val dayTest1 = listOf(
        "1abc2",
        "pqr3stu8vwx",
        "a1b2c3d4e5f",
        "treb7uchet",
    )
    val dayTest2 = listOf(
        "two1nine",
        "eightwothree",
        "abcone2threexyz",
        "xtwone3four",
        "4nineeightseven2",
        "zoneight234",
        "7pqrstsixteen"
    )
    
    Day01.part1(dayInput)
    Day01.part2(dayInput)

}
