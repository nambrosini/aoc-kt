package aoc2019

import util.intcode.Computer
import util.intcode.parseMemory
import util.loc.Dir
import util.loc.Pos
import util.print
import util.readInput

object Day11 {
    fun part1(input: String): Int {
        val memory = parseMemory(input)
        val map = mutableMapOf<Pos, Long>()

        val pc = Computer(memory)

        var currentPos = Pos(0, 0)
        var dir = Dir.U

        while (true) {
            val color = pc.run(mutableListOf(map.getOrDefault(currentPos, 0)))
            map[currentPos] = color ?: break
            val turn = pc.run(mutableListOf()) ?: break
            dir = dir.turn(turn == 1L)

            currentPos += dir
        }

        return map.size
    }

    fun part2(input: String): String {
        val memory = parseMemory(input)
        val map = mutableMapOf<Pos, Long>(Pair(Pos(0, 0), 1L))

        val pc = Computer(memory)

        var currentPos = Pos(0, 0)
        var dir = Dir.U

        while (true) {
            val color = pc.run(mutableListOf(map.getOrDefault(currentPos, 0)))
            map[currentPos] = color ?: break
            val turn = pc.run(mutableListOf()) ?: break
            dir = dir.turn(turn == 1L)

            currentPos += dir
        }

        return map.print()
    }
}

fun main() {
    val input = readInput(2019, 11)
    println("Part 1: ${Day11.part1(input)}")
    println("Part 2: ${Day11.part2(input)}")
}
