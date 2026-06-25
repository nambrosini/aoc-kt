package aoc2019

import util.intcode.Computer
import util.intcode.parseMemory
import util.permutations
import util.readInput

object Day07 {
    fun part1(input: String): Int {
        val memory = parseMemory(input)

        var max = 0

        for (phases in (0..4).permutations()) {
            var previous = 0
            for (phase in phases) {
                val pc = Computer(memory.toMutableList())
                previous = pc.run(mutableListOf(phase, previous))!!
            }
            max = maxOf(max, previous)
        }
        return max
    }

    fun part2(input: String): Int {
        val memory = parseMemory(input)

        var max = 0

        for (phases in (5..9).permutations()) {
            // Init
            val amplifiers = mutableListOf<Computer>()
            var previous = 0
            for (phase in phases) {
                val pc = Computer(memory.toMutableList())
                previous = pc.run(mutableListOf(phase, previous)) ?: error("Output expected")
                amplifiers.add(pc)
            }

            var counter = 0
            while (true) {
                previous = amplifiers[counter].run(mutableListOf(previous)) ?: break
                counter = (counter + 1) % amplifiers.size
            }

            max = maxOf(max, previous)
        }

        return max
    }
}

fun main() {
    val input = readInput(2019, 7)
    println("Part 1: ${Day07.part1(input)}")
    println("Part 2: ${Day07.part2(input)}")
}
