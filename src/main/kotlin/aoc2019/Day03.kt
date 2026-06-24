package aoc2019

import util.readInput
import util.loc.*

object Day03 {
    fun part1(input: List<String>): Int {
        var wires = mutableListOf<MutableSet<Pos>>()
        for (instructions in input) {
            var pos = Pos(0, 0)
            var wire = mutableSetOf<Pos>()
            for (instruction in instructions.split(",")) {
                val dir = instruction[0].toDir()
                var steps = instruction.substring(1..<instruction.length).toInt()
                for (step in 0..<steps) {
                    pos += dir
                    wire.add(pos)
                }
            }
            wires.add(wire)
        }

        return wires[0].filter { wires[1].contains(it) }
            .minOf { it.dist(Pos(0, 0)) }
    }

    fun part2(input: List<String>): Int {
        var wires = mutableListOf<MutableSet<Pair<Pos, Int>>>()
        for (instructions in input) {
            var pos = Pos(0, 0)
            var totalSteps = 0
            var wire = mutableSetOf<Pair<Pos, Int>>()
            for (instruction in instructions.split(",")) {
                val dir = instruction[0].toDir()
                var steps = instruction.substring(1..<instruction.length).toInt()
                for (step in 0..<steps) {
                    totalSteps += 1
                    pos += dir
                    wire.add(Pair(pos, totalSteps))
                }
            }
            wires.add(wire)
        }

        return wires[0].filter { pos -> wires[1].any { it.first == pos.first } }
            .minOfOrNull { pos -> pos.second + wires[1].find { it.first == pos.first }!!.second }!!
    }
}

fun main() {
    val input = readInput(2019, 3)
    println("Part 1: ${Day03.part1(input)}")
    println("Part 2: ${Day03.part2(input)}")
}
