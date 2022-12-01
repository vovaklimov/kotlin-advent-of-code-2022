import java.io.File

fun main() {
    val input = readInput()

    println("Part 1: ${getRichestElfCalories(input)}")
    println("Part 2: ${getTotalOfTopThree(input)}")
}

fun getRichestElfCalories(input: String): Number = input.split("\n\n").maxOf {
    it.split("\n").sumOf(String::toInt)
}

fun getTotalOfTopThree(input: String): Number = input.split("\n\n").map {
    it.split("\n").sumOf(String::toInt)
}.sortedDescending().take(3).sum()

fun readInput(): String {
    val path = ClassLoader.getSystemClassLoader().getResource("input.txt")?.path.toString()
    return File(path).readText(Charsets.UTF_8).trim()
}
