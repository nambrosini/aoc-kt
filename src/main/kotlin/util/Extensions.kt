package util

import util.loc.Pos

fun Map<Pos, Long>.print(): String {
    val minX = this.keys.minOf { it.x }
    val minY = this.keys.minOf { it.y }
    val maxX = this.keys.maxOf { it.x }
    val maxY = this.keys.maxOf { it.y }

    val builder = StringBuilder("\n")

    for (y in minY..maxY) {
        for (x in minX..maxX) {
            if (this[Pos(x, y)] == 1L) {
                builder.append("█")
            } else {
                builder.append(" ")
            }
        }
        builder.append('\n')
    }

    return builder.toString()
}
