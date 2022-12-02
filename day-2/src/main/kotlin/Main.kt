import java.io.File

enum class Outcome(val score: Int) {
    Win(6),
    Draw(3),
    Loose(0)
}

const val Rock = "Rock"
const val Paper = "Paper"
const val Scissors = "Scissors"

data class Move(val name: String, val win: String, val lose: String)

val inputToMove = mapOf(
    "A" to Move(name = Rock, win = Scissors, lose = Paper),
    "B" to Move(name = Paper, win = Rock, lose = Scissors),
    "C" to Move(name = Scissors, win = Paper, lose = Rock),
    "X" to Move(name = Rock, win = Scissors, lose = Paper),
    "Y" to Move(name = Paper, win = Rock, lose = Scissors),
    "Z" to Move(name = Scissors, win = Paper, lose = Rock),
)

val scores = mapOf(
    Rock to 1,
    Paper to 2,
    Scissors to 3,
)

fun part1(input: List<List<String>>): Int {
    return input.fold(0) { score, (opponentInput, myInput) ->
        val opponentMove = inputToMove.getOrElse(opponentInput) {
            throw RuntimeException("Invalid input $opponentInput")
        }

        val myMove = inputToMove.getOrElse(myInput) {
            throw RuntimeException("Invalid input $myInput")
        }

        val myMoveScore = scores.getOrDefault(myMove.name, 0)

        score + myMoveScore + when {
            opponentMove.name == myMove.name -> Outcome.Draw.score
            opponentMove.win == myMove.name -> Outcome.Loose.score
            else -> Outcome.Win.score
        }
    }
}

fun part2(input: List<List<String>>): Int {
    return input.fold(0) { score, (opponentInput, result) ->
        val opponentMove = inputToMove.getOrElse(opponentInput) {
            throw RuntimeException("Invalid input $opponentInput")
        }

        score + when (result) {
            "X" -> scores.getOrDefault(opponentMove.win, 0) + Outcome.Loose.score
            "Y" -> scores.getOrDefault(opponentMove.name, 0) + Outcome.Draw.score
            else -> scores.getOrDefault(opponentMove.lose, 0) + Outcome.Win.score
        }
    }
}

fun main() {
    val input = readInput()
    println("Part1 score is ${part1(input)}")
    println("Part2 score is ${part2(input)}")
}

fun readInput(): List<List<String>> {
    val path = ClassLoader.getSystemClassLoader().getResource("input.txt")?.path.toString()
    return File(path).readLines(Charsets.UTF_8).toList().map {
        it.split(" ")
    }
}
