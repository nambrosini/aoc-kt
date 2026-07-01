package util.loc

data class Pos3D(
    var x: Long,
    var y: Long,
    var z: Long,
) {
    constructor(x: Int, y: Int, z: Int) : this(x.toLong(), y.toLong(), z.toLong())

    operator fun plus(other: Pos3D): Pos3D = Pos3D(this.x + other.x, this.y + other.y, this.z + other.z)
}

fun String.toPos3D(): Pos3D {
    val res = Regex("""<x=(-?\d+), y=(-?\d+), z=(-?\d+)>""").find(this) ?: error("merda")
    return Pos3D(res.groupValues[1].toInt(), res.groupValues[2].toInt(), res.groupValues[3].toInt())
}
