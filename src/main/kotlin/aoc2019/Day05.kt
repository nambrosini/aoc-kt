package aoc2019

import util.intcode.Computer
import util.readInput

object Day05 {
    fun part1(input: List<String>): Int {
        val memory = input[0].split(",").map { it.toInt() }
        val pc = Computer(memory.toMutableList())
        var result = 0

        while (true) {
            val x = pc.run(1)
            x ?: break
            result = x
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val memory = input[0].split(",").map { it.toInt() }
        val pc = Computer(memory.toMutableList())
        return pc.run(5)!!
    }
}

fun main() {
    val input = readInput(2019, 5)
    println("Part 1: ${Day05.part1(input)}")
    println("Part 2: ${Day05.part2(input)}")
}
