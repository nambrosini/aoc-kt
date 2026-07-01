package util.loc

import util.math.mcd
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.atan2

data class Pos(
    var x: Int,
    var y: Int,
) {
    fun dist(other: Pos): Int = (other.x - x).absoluteValue + (other.y - y).absoluteValue

    fun calcAngle(other: Pos): Int {
        val dx = other.x.toDouble() - this.x.toDouble()
        val dy = other.y.toDouble() - this.y.toDouble()
        var angle = atan2(dx, -dy)
        if (angle < 0) angle += 2 * PI
        return (angle * 1000000).toInt()
    }

    fun getStep(other: Pos): Pos {
        val dx = other.x - this.x
        val dy = other.y - this.y
        val mcd = mcd(dx, dy)
        return Pos(dx / mcd, dy / mcd)
    }

    operator fun plus(other: Pos): Pos = Pos(x + other.x, y + other.y)

    operator fun plus(other: Dir): Pos {
        val dir = other.toPos()
        return Pos(x + dir.x, y + dir.y)
    }

    operator fun times(coeff: Double): Pos = Pos((x.toDouble() * coeff).toInt(), (y.toDouble() * coeff).toInt())
}
