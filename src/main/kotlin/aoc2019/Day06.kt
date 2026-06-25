package aoc2019

import util.readInput

object Day06 {
    const val START = "YOU"
    const val TARGET = "SAN"

    fun parse(input: List<String>): MutableMap<String, MutableSet<String>> {
        val map = mutableMapOf<String, MutableSet<String>>()

        for (l in input) {
            val a = l.substringAfter(")")
            val b = l.substringBefore(")")

            map.getOrPut(a) { mutableSetOf() }.add(b)
        }

        return map
    }

    fun part1(input: List<String>): Int {
        val map = parse(input)

        return map.keys.sumOf { recurse(map, it) }
    }

    fun part2(input: List<String>): Int {
        val map = parse(input)

        return recurse2(map, mutableSetOf(), START)!!
    }

    fun recurse(
        map: MutableMap<String, MutableSet<String>>,
        current: String,
    ): Int {
        if (current !in map) {
            return 0
        }

        return map[current]!!.sumOf { recurse(map, it) } + 1
    }

    fun recurse2(
        map: MutableMap<String, MutableSet<String>>,
        visited: MutableSet<String>,
        current: String,
    ): Int? {
        if (current == TARGET) {
            return visited.size - 2
        }

        visited.add(current)

        val connections =
            (
                map.getOrDefault(
                    current,
                    mutableSetOf(),
                ) + map.filter { it.value.contains(current) }.keys
            ).filter { !visited.contains(it) }

        if (connections.isEmpty()) {
            return null
        }

        return connections
            .mapNotNull { recurse2(map, visited.toMutableSet(), it) }
            .minOfOrNull { it }
    }
}

fun main() {
    val input = readInput(2019, 6)
    println("Part 1: ${Day06.part1(input)}")
    println("Part 2: ${Day06.part2(input)}")
}
