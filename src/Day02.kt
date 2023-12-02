object Day02 {
    fun part1(inputs: List<String>) {
        val targetRed = 12
        val targetGreen = 13
        val targetBlue = 14

        inputs.mapNotNull { line ->
            val gameId = """^Game (\d+)""".toRegex().find(line)!!.destructured.component1().toInt()
            var isValid = true
            line.split(";").map { splitLine ->
                val blue = """(\d+) blue""".toRegex().findAll(splitLine).toList().map { it.destructured.component1() }
                val reds = """(\d+) red""".toRegex().findAll(splitLine).toList().map { it.destructured.component1() }
                val greens = """(\d+) green""".toRegex().findAll(splitLine).toList().map { it.destructured.component1() }
                if (blue.sumOf { it.toInt() } > targetBlue || greens.sumOf { it.toInt() } > targetGreen || reds.sumOf { it.toInt() } > targetRed) {
                    isValid = false
                } else null
            }

            if(isValid) {
                gameId
            } else null

        }.sumOf { it }.println()
    }

    fun part2(inputs: List<String>) {

        inputs.mapNotNull { line ->
            var lineMinBlue = 0
            var lineMinGreen = 0
            var lineMinRed = 0

            line.split(";").map { splitLine ->
                val blues = """(\d+) blue""".toRegex().findAll(splitLine).toList().map { it.destructured.component1() }
                val reds = """(\d+) red""".toRegex().findAll(splitLine).toList().map { it.destructured.component1() }
                val greens = """(\d+) green""".toRegex().findAll(splitLine).toList().map { it.destructured.component1() }

                val minBlue = blues.maxOfOrNull { it.toInt() }
                val minGreen = greens.maxOfOrNull { it.toInt() }
                val minRed = reds.maxOfOrNull { it.toInt() }

                if (minBlue != null && lineMinBlue < minBlue) {
                    lineMinBlue = minBlue
                }
                if (minGreen != null && lineMinGreen < minGreen) {
                    lineMinGreen = minGreen
                }
                if (minRed != null && lineMinRed < minRed) {
                    lineMinRed = minRed
                }
            }

            lineMinRed * lineMinGreen * lineMinBlue

        }.sumOf { it }.println()
    }

}

fun main() {
    val dayInput = readInput("Day02")
//    val dayTest1 = readInput("Day02Test1")
//    val dayTest2 = readInput("Day02Test2")

//    Day02.part1(dayInput)
//    Day02.part2(dayInput)

}
