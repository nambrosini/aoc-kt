package aoc2019

import util.readInput

object Day04 {
    private fun count(input: List<String>, pred: (Int) -> Boolean): Int {
        val (start, end) = input[0].split("-").map { it.toInt() }
        return (start..end).count(pred)
    }

    fun part1(input: List<String>): Int = count(input, ::isValid)

    fun part2(input: List<String>): Int = count(input, ::isValidPart2)

    fun isValid(num: Int): Boolean {
        var x = num
        var previous = 10
        var hasDouble = false

        do {
            val current = x % 10
            if (current == previous) {
                hasDouble = true
            }
            if (current > previous) {
                return false
            }
            x /= 10
            previous = current
        } while (x > 0)

        return hasDouble
    }

    fun isValidPart2(num: Int): Boolean {
        var x = num
        var previous = 10
        var hasDouble = false

        do {
            val current = x % 10
            x /= 10
            if (current == previous && !hasDouble) {
                hasDouble = true
                if (current == x % 10) {
                    hasDouble = false
                }

                if (x > 1000 && current == (x / 100) % 10) {
                    hasDouble = false
                }
            }
            if (current > previous) {
                return false
            }
            previous = current
        } while (x > 0)

        return hasDouble
    }
}

fun main() {
    val input = readInput(2019, 4)
    println("Part 1: ${Day04.part1(input)}")
    println("Part 2: ${Day04.part2(input)}")
}
