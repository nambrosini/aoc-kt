package util.intcode

enum class OpCode(val code: Int) {
    Add(1),
    Mult(2),
    Exit(99);
}

fun Int.toOpcode(): OpCode =
    OpCode.entries.find { it.code == this } ?: throw IllegalArgumentException("No Opcode for value $this")
