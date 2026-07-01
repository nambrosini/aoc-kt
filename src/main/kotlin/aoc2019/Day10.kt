package aoc2019

import toGrid
import util.loc.Pos
import util.readInput

object Day10 {
    fun part1(input: String): Int {
        val grid = input.toGrid()

        val asteroids = grid.findAll('#')
        val base = bestBase(asteroids)
        return base.second
    }

    fun part2(input: String): Long {
        val grid = input.toGrid()
        var found = grid.findAll('#')
        val base = bestBase(found).first
        val asteroids = found.filter { it != base }.map { it.toAsteroid(base) }.toMutableList()

        var destroyed = 0

        while (true) {
            var angles = asteroids.map { it.angle }.toMutableSet()
            if (angles.isEmpty()) {
                break
            }
            while (angles.isNotEmpty()) {
                val min = angles.min()
                val index =
                    asteroids
                        .withIndex()
                        .filter { (index, it) -> it.angle == min }
                        .minBy { (index, it) -> it.dist }
                        .index
                destroyed++
                if (destroyed == 200) {
                    val asteroid = asteroids[index]
                    return 100 * asteroid.pos.x + asteroid.pos.y
                }

                angles.remove(min)
                asteroids.removeAt(index)
            }
        }

        error("che palle")
    }

    data class Asteroid(
        val pos: Pos,
        val angle: Long,
        val dist: Long,
    )

    fun Pos.toAsteroid(base: Pos): Asteroid = Asteroid(this, base.calcAngle(this), base.dist(this))

    fun bestBase(asteroids: List<Pos>): Pair<Pos, Int> = asteroids.map { Pair(it, asteroidsSeen(asteroids, it)) }.maxBy { it.second }

    fun asteroidsSeen(
        grid: List<Pos>,
        pos: Pos,
    ): Int =
        grid
            .filter { it != pos }
            .map { pos.getStep(it) }
            .toSet()
            .size
}

fun main() {
    val input = readInput(2019, 10)
    println("Part 1: ${Day10.part1(input)}")
    println("Part 2: ${Day10.part2(input)}")
}
