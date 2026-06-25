package aoc2019

import util.intcode.Computer
import util.intcode.parseMemory
import util.readInput

object Day09 {
    fun part1(input: String): Long? {
        val pc = Computer(parseMemory(input))
        return pc.run(mutableListOf(1))
    }

    fun part2(input: String): Long? {
        val pc = Computer(parseMemory(input))
        return pc.run(mutableListOf(2))
    }
}

fun main() {
    val input = readInput(2019, 9)
    println("Part 1: ${Day09.part1(input)}")
    println("Part 2: ${Day09.part2(input)}")
}
