package util.loc

import kotlin.math.absoluteValue

data class Pos(var x: Int, var y: Int) {
    fun dist(other: Pos): Int {
        return (other.x - x).absoluteValue + (other.y - y).absoluteValue
    }

    operator fun plus(other: Pos): Pos {
        return Pos(x + other.x, y + other.y)
    }

    operator fun plus(other: Dir): Pos {
        val dir = other.toPos()
        return Pos(x + dir.x, y + dir.y)
    }
}
