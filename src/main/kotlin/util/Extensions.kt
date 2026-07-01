package util

import util.loc.Pos

fun Map<Pos, Long>.print(characters: List<Char>): String {
    val minX = this.keys.minOf { it.x }
    val minY = this.keys.minOf { it.y }
    val maxX = this.keys.maxOf { it.x }
    val maxY = this.keys.maxOf { it.y }

    val builder = StringBuilder("\n")

    for (y in minY..maxY) {
        for (x in minX..maxX) {
            builder.append(characters[this[Pos(x, y)]!!.toInt()])
        }
        builder.append('\n')
    }

    return builder.toString()
}

fun Int.cmp(other: Int): Int =
    if (this > other) {
        -1
    } else if (this == other) {
        0
    } else {
        1
    }
