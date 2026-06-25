package aoc2019

import util.intcode.Computer
import util.intcode.parseMemory
import util.readInput

object Day05 {
    fun part1(input: String): Long {
        val pc = Computer(parseMemory(input).copyOf())
        var result = 0L

        while (true) {
            val x = pc.run(mutableListOf<Long>(1L))
            x ?: break
            result = x
        }

        return result
    }

    fun part2(input: String): Long {
        val pc = Computer(parseMemory(input).copyOf())
        return pc.run(mutableListOf(5L))!!
    }
}

fun main() {
    val input = readInput(2019, 5)
    println("Part 1: ${Day05.part1(input)}")
    println("Part 2: ${Day05.part2(input)}")
}
