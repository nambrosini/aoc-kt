package aoc2019

import util.intcode.Computer
import util.intcode.parseMemory
import util.readInput

object Day05 {
    fun part1(input: List<String>): Int {
        val pc = Computer(parseMemory(input).toMutableList())
        var result = 0

        while (true) {
            val x = pc.run(mutableListOf(1))
            x ?: break
            result = x
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val pc = Computer(parseMemory(input).toMutableList())
        return pc.run(mutableListOf(5))!!
    }
}

fun main() {
    val input = readInput(2019, 5)
    println("Part 1: ${Day05.part1(input)}")
    println("Part 2: ${Day05.part2(input)}")
}
