package aoc2019

import util.readInput

object Day08 {
    fun parse(
        input: String,
        width: Int,
        height: Int,
    ): List<String> = input.trim().chunked(width * height)

    fun part1(
        input: String,
        width: Int,
        height: Int,
    ): Int {
        val image = parse(input, width, height)

        val fewest0 = image.take(image.size - 1).minBy { it.count { char -> char == '0' } }
        return fewest0.count { it == '1' } * fewest0.count { it == '2' }
    }

    fun part2(
        input: String,
        width: Int,
        height: Int,
    ): String {
        val layers = parse(input, width, height)

        var finalImage = layers[0].toCharArray()

        for ((index, pixel) in finalImage.withIndex()) {
            if (pixel == '2') {
                for (layer in layers) {
                    if (layer[index] != '2') {
                        finalImage[index] = layer[index]
                        break
                    }
                }
            }
        }

        var result = StringBuilder()

        for ((index, pixel) in finalImage.withIndex()) {
            if (index % width == 0) {
                result.append('\n')
            }
            result.append(if (pixel == '0') ' ' else '#')
        }
        return result.toString()
    }
}

fun main() {
    val input = readInput(2019, 8)
    println("Part 1: ${Day08.part1(input, 25, 6)}")
    println("Part 2: ${Day08.part2(input, 25, 6)}")
}
