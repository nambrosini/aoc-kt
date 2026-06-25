package util.intcode

enum class Mode {
    Pos,
    Imm,
    Rel,
}

fun Long.toMode(): Mode =
    when (this) {
        0L -> Mode.Pos
        1L -> Mode.Imm
        2L -> Mode.Rel
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

    data class Arb(
        val m1: Mode,
    ) : OpCode()

    object Exit : OpCode()
}

fun Long.toOpcode(): OpCode {
    val op = this % 100
    val m1 = ((this / 100) % 10).toMode()
    val m2 = ((this / 1000) % 10).toMode()
    val m3 = ((this / 10000) % 10).toMode()

    return when (op) {
        1L -> OpCode.Add(m1, m2, m3)
        2L -> OpCode.Mult(m1, m2, m3)
        3L -> OpCode.Save(m1)
        4L -> OpCode.Out(m1)
        5L -> OpCode.Jit(m1, m2)
        6L -> OpCode.Jif(m1, m2)
        7L -> OpCode.Lt(m1, m2, m3)
        8L -> OpCode.Eq(m1, m2, m3)
        9L -> OpCode.Arb(m1)
        99L -> OpCode.Exit
        else -> error("No Opcode for value $this")
    }
}
