package aoc2019

import util.readInput
import util.intcode.Computer

object Day02 {
    fun part1(input: List<String>): Int {
        val memory = input[0].split(',').map { it.toInt() }

        val pc = Computer(memory.toMutableList())
        pc.run(12, 2)
        return pc.memory[0]
    }

    fun part2(input: List<String>): Int {
        val memory = input[0].split(',').map { it.toInt() }

        for (a in 0..100) {
            for (b in 0..100) {
                val pc = Computer(memory.toMutableList())
                pc.run(a, b)

                if (pc.memory[0] == 19690720) {
                    return 100 * a + b
                }
            }
        }

        return -1
    }
}

fun main() {
    val input = readInput(2019, 2)
    println("Part 1: ${Day02.part1(input)}")
    println("Part 2: ${Day02.part2(input)}")
}
