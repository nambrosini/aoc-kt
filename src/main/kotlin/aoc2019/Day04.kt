package aoc2019

import util.readInput

object Day04 {
    fun part1(input: List<String>): Int {
        var passwords = 0
        var start = input[0].substringBefore("-").toInt()
        var end = input[0].substringAfter("-").toInt()

        for (p in start..end) {
            if (isValid(p)) {
                passwords++
            }
        }
        return passwords
    }

    fun part2(input: List<String>): Int {
        var passwords = 0
        var start = input[0].substringBefore("-").toInt()
        var end = input[0].substringAfter("-").toInt()

        for (p in start..end) {
            if (isValidPart2(p)) {
                passwords++
            }
        }
        return passwords
    }

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
