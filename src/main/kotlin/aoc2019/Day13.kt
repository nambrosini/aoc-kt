package aoc2019

import util.intcode.Computer
import util.intcode.parseMemory
import util.loc.Pos
import util.print
import util.readInput

object Day13 {
    fun part1(input: String): Int {
        val memory = parseMemory(input)
        val pc = Computer(memory)
        var map = mutableMapOf<Pos, Long>()
        var blocks = 0

        while (true) {
            val pos = Pos(0, 0)
            pos.x = pc.run(mutableListOf()) ?: break
            pos.y = pc.run(mutableListOf()) ?: break
            val id = pc.run(mutableListOf()) ?: break
            if (id == 2L) {
                blocks++
            }
            map[pos] = id
        }

        return blocks
    }

    fun part2(input: String): Long {
        val memory = parseMemory(input)
        memory[0] = 2
        val pc = Computer(memory)
        var map = mutableMapOf<Long, Pos>()

        var score = 0L
        var input = 0L

        while (true) {
            val pos = Pos(0, 0)
            pos.x = pc.run(mutableListOf(input)) ?: break
            pos.y = pc.run(mutableListOf()) ?: break
            val id = pc.run(mutableListOf()) ?: break

            when (pos.x) {
                -1L -> score = id
                else -> map[id] = pos
            }

            val paddleX = map.getOrDefault(3L, Pos(0, 0)).x
            val ballX = map.getOrDefault(4L, Pos(0, 0)).x

            input = ballX.compareTo(paddleX).toLong()
        }

        return score
    }
}

fun main() {
    val input = readInput(2019, 13)
    println("Part 1: ${Day13.part1(input)}")
    println("Part 2: ${Day13.part2(input)}")
}
