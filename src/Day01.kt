fun main() {
    fun Pair<String, String>.replacement() = second + first.substring(1)

    fun findCalibrationCodePartOne(input: List<String>): Long {
        // Calculate the code
        var sum = 0L
        input.map { calibrationLine ->
            var firstDigit: Char? = null
            var lastDigit: Char? = null
            for (index in calibrationLine.indices) {
                calibrationLine[index].takeIf { it.isDigit() }?.let { digit ->
                    if (firstDigit == null) firstDigit = digit
                    lastDigit = digit
                }
            }
            sum += ("$firstDigit$lastDigit").toInt()
        }
        return sum
    }

    fun findCalibrationCodePartTwo(input: List<String>): Long {
        // Mapper
        val mapper = arrayOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )
        // Remap values
        val adjustedInput = input.map { oldCalibrationLine ->
            var newCalibrationLine = oldCalibrationLine
            for (index in oldCalibrationLine.indices) {
                mapper.forEach { mapperPair ->
                    val substring = oldCalibrationLine.substring(index)
                    if (substring.startsWith(mapperPair.first)) {
                        newCalibrationLine = newCalibrationLine.replace(
                            oldValue = substring,
                            newValue = substring.replaceFirst(mapperPair.first, mapperPair.replacement()),
                        )
                    }
                }
            }
            newCalibrationLine
        }
        return findCalibrationCodePartOne(adjustedInput)
    }

    val calibrationDocument = readInput("Day_01_calibration")
    println(findCalibrationCodePartOne(calibrationDocument))
    println(findCalibrationCodePartTwo(calibrationDocument))
}
