package aoc2019

import util.loc.Pos3D
import util.loc.toPos3D
import util.math.lcm
import util.readInput
import kotlin.math.absoluteValue

object Day12 {
    fun part1(
        input: String,
        simulations: Int,
    ): Int {
        var moons: List<Moon> = input.trim().lines().map { Moon(it.toPos3D()) }

        for (i in 1..simulations) {
            moons.calcGravity()
            moons.applyVelocity()
        }

        return moons.calcEnergy()
    }

    fun part2(input: String): Long {
        val moons = input.trim().lines().map { Moon(it.toPos3D()) }
        var pastX = mutableSetOf<List<Pair<Long, Long>>>()
        var pastY = mutableSetOf<List<Pair<Long, Long>>>()
        var pastZ = mutableSetOf<List<Pair<Long, Long>>>()

        var turnX: Long? = null
        var turnY: Long? = null
        var turnZ: Long? = null

        var turn = 0L
        while (true) {
            val stateX: List<Pair<Long, Long>> = moons.map { Pair(it.pos.x, it.vel.x) }
            val stateY: List<Pair<Long, Long>> = moons.map { Pair(it.pos.y, it.vel.y) }
            val stateZ: List<Pair<Long, Long>> = moons.map { Pair(it.pos.z, it.vel.z) }

            if (pastX.contains(stateX) && turnX == null) {
                turnX = turn
            } else {
                pastX.add(stateX)
            }
            if (pastY.contains(stateY) && turnY == null) {
                turnY = turn
            } else {
                pastY.add(stateY)
            }
            if (pastZ.contains(stateZ) && turnZ == null) {
                turnZ = turn
            } else {
                pastZ.add(stateZ)
            }

            if (turnX != null && turnY != null && turnZ != null) {
                println("$turnX, $turnY, $turnZ")
                return lcm(lcm(turnX, turnY), turnZ)
            }

            moons.calcGravity()
            moons.applyVelocity()
            turn++
        }

        error("fiuck")
    }

    data class Moon(
        var pos: Pos3D,
        var vel: Pos3D = Pos3D(0, 0, 0),
    ) {
        fun applyVelocity() {
            pos += vel
        }

        fun calcEnergy(): Int =
            (
                (pos.x.absoluteValue + pos.y.absoluteValue + pos.z.absoluteValue) *
                    (
                        vel.x.absoluteValue + vel.y.absoluteValue +
                            vel.z.absoluteValue
                    )
            ).toInt()
    }

    fun List<Moon>.applyVelocity() {
        this.forEach { it.applyVelocity() }
    }

    fun List<Moon>.calcGravity() {
        for (moon in this) {
            for (other in this.filter { it != moon }) {
                moon.vel.x += -moon.pos.x.compareTo(other.pos.x)
                moon.vel.y += -moon.pos.y.compareTo(other.pos.y)
                moon.vel.z += -moon.pos.z.compareTo(other.pos.z)
            }
        }
    }

    fun List<Moon>.calcEnergy(): Int = this.sumOf { it.calcEnergy() }
}

fun main() {
    val input = readInput(2019, 12)
    println("Part 1: ${Day12.part1(input, 1000)}")
    println("Part 2: ${Day12.part2(input)}")
}
