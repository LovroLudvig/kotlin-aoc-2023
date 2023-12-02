import java.util.Collections.max

fun main() {
    val gameNumberRegex = """(?<=Game )\d+"""
    val redRegex = """\d+(?= red)"""
    val greenRegex = """\d+(?= green)"""
    val blueRegex = """\d+(?= blue)"""

    val legalRedNumber = 12
    val legalGreenNumber = 13
    val legalBlueNumber = 14

    fun String.gameNumber() = gameNumberRegex.toRegex().find(this)!!.value.toInt()
    fun String.findReds() = redRegex.toRegex().findAll(this).map { it.value.toInt() }.toList()
    fun String.findGreens() = greenRegex.toRegex().findAll(this).map { it.value.toInt() }.toList()
    fun String.findBlues() = blueRegex.toRegex().findAll(this).map { it.value.toInt() }.toList()

    fun checkLegalGames(games: List<String>): Long {
        var sum = 0L
        games.forEach { game ->
            val isLegalGame = game.findReds().all { it <= legalRedNumber }
                    && game.findGreens().all { it <= legalGreenNumber }
                    && game.findBlues().all { it <= legalBlueNumber }
            if (isLegalGame) sum += game.gameNumber()
        }
        return sum
    }

    fun powerOfSets(games: List<String>): Long {
        var sum = 0L
        games.forEach { game ->
            val minReds = max(game.findReds())
            val minGreens = max(game.findGreens())
            val minBlues = max(game.findBlues())
            sum += minReds * minGreens * minBlues
        }
        return sum
    }

    val games = readInput("Day_02_games")
    println(checkLegalGames(games))
    println(powerOfSets(games))
}
