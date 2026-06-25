package aoc2019

import util.readInput

object Day01 {
    fun part1(input: String): Any = input.lines().sumOf { it.toInt() / 3 - 2 }

    fun part2(input: String): Int {
        var sum = 0
        for (x in input.lines()) {
            var num = x.toInt()

            while (num > 6) {
                num = num / 3 - 2
                sum += num
            }
        }

        return sum
    }
}

fun main() {
    val input = readInput(2019, 1)
    println("Part 1: ${Day01.part1(input)}")
    println("Part 2: ${Day01.part2(input)}")
}
