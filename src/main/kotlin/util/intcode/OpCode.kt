package util.intcode

enum class Mode {
    Pos,
    Imm,
}

fun Int.toMode(): Mode =
    when (this) {
        0 -> Mode.Pos
        1 -> Mode.Imm
        else -> error("No Mode for value $this")
    }

sealed class OpCode {
    data class Add(
        val m1: Mode,
        val m2: Mode,
        val m3: Mode,
    ) : OpCode()

    data class Mult(
        val m1: Mode,
        val m2: Mode,
        val m3: Mode,
    ) : OpCode()

    data class Save(
        val m1: Mode,
    ) : OpCode()

    data class Out(
        val m1: Mode,
    ) : OpCode()

    data class Jit(
        val m1: Mode,
        val m2: Mode,
    ) : OpCode()

    data class Jif(
        val m1: Mode,
        val m2: Mode,
    ) : OpCode()

    data class Lt(
        val m1: Mode,
        val m2: Mode,
        val m3: Mode,
    ) : OpCode()

    data class Eq(
        val m1: Mode,
        val m2: Mode,
        val m3: Mode,
    ) : OpCode()

    object Exit : OpCode()
}

fun Int.toOpcode(): OpCode {
    val op = this % 100
    val m1 = ((this / 100) % 10).toMode()
    val m2 = ((this / 1000) % 10).toMode()
    val m3 = ((this / 10000) % 10).toMode()

    return when (op) {
        1 -> OpCode.Add(m1, m2, m3)
        2 -> OpCode.Mult(m1, m2, m3)
        3 -> OpCode.Save(m1)
        4 -> OpCode.Out(m1)
        5 -> OpCode.Jit(m1, m2)
        6 -> OpCode.Jif(m1, m2)
        7 -> OpCode.Lt(m1, m2, m3)
        8 -> OpCode.Eq(m1, m2, m3)
        99 -> OpCode.Exit
        else -> error("No Opcode for value $this")
    }
}
