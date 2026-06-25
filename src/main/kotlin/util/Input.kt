package util

import java.io.File

fun readInput(
    year: Int,
    day: Int,
): String = File("data/input/$year/$day.txt").readText()

fun exampleExists(
    year: Int,
    day: Int,
    part: Int,
): Boolean {
    val specific = File("data/examples/$year/day${day}_$part.txt")
    val general = File("data/examples/$year/day$day.txt")
    return specific.exists() || general.exists()
}

fun readExample(
    year: Int,
    day: Int,
    part: Int,
): String {
    val specific = File("data/examples/$year/day${day}_$part.txt")
    val general = File("data/examples/$year/day$day.txt")
    return (if (specific.exists()) specific else general).readText()
}
