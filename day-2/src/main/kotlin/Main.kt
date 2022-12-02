import java.io.File

enum class Outcome(val score: Int) {
    Win(6),
    Draw(3),
    Loose(0)
}

fun part1(input: List<List<String>>): Int {
    val wins = mapOf(
        "A" to "Z",
        "C" to "Y",
        "B" to "X",
    )

    val draws = mapOf(
        "A" to "X",
        "B" to "Y",
        "C" to "Z",
    )

    val scores = mapOf(
        "X" to 1,
        "Y" to 2,
        "Z" to 3,
    )

    return input.fold(0) { score, (opponentMove, myMove) ->
        score + when {
            wins[opponentMove]?.contains(myMove) == true -> Outcome.Loose.score
            draws[opponentMove]?.contains(myMove) == true -> Outcome.Draw.score
            else -> Outcome.Win.score
        } + scores.getOrDefault(myMove, 0)
    }
}

fun part2(input: List<List<String>>): Int {
    val wins = mapOf(
        "A" to "C",
        "C" to "B",
        "B" to "A",
    )

    val loses = mapOf(
        "C" to "A",
        "B" to "C",
        "A" to "B",
    )

    val scores = mapOf(
        "A" to 1,
        "B" to 2,
        "C" to 3,
    )

    return input.fold(0) { score, (opponentMove, result) ->
        score + when (result) {
            "X" -> scores[wins[opponentMove]]!! + Outcome.Loose.score
            "Y" -> scores[opponentMove]!! + Outcome.Draw.score
            else -> scores[loses[opponentMove]]!! + Outcome.Win.score
        }
    }
}

fun main() {
    val input = readInput()
    println("Part1 score is ${part1(input)}!")
    println("Part2 score is ${part2(input)}!")
}

fun readInput(): List<List<String>> {
    val path = ClassLoader.getSystemClassLoader().getResource("input.txt")?.path.toString()
    return File(path).readLines(Charsets.UTF_8).toList().map {
        it.split(" ")
    }
}
