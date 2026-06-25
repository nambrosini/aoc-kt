package util.loc

enum class Dir {
    U,
    R,
    D,
    L,
    ;

    fun toPos(): Pos {
        return when (this) {
            U -> Pos(0, -1)
            R -> Pos(1, 0)
            D -> Pos(0, 1)
            L -> Pos(-1, 0)
        }
    }
}

fun Char.toDir(): Dir =
    when (this) {
        'U' -> Dir.U
        'R' -> Dir.R
        'D' -> Dir.D
        'L' -> Dir.L
        else -> error("Unexpected value: $this")
    }
