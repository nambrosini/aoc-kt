package util.intcode

fun parseMemory(input: String): List<Int> = input.split(",").map { it.toInt() }

class Computer(
    val memory: MutableList<Int>,
) {
    var pointer: Int = 0

    fun run(input: MutableList<Int>): Int? {
        while (true) {
            when (val opcode = memory[pointer].toOpcode()) {
                is OpCode.Add -> {
                    val v1 = getMem(1, opcode.m1)
                    val v2 = getMem(2, opcode.m2)

                    setMem(3, opcode.m3, v1 + v2)
                    pointer += 4
                }

                is OpCode.Mult -> {
                    val v1 = getMem(1, opcode.m1)
                    val v2 = getMem(2, opcode.m2)

                    setMem(3, opcode.m3, v1 * v2)
                    pointer += 4
                }

                is OpCode.Save -> {
                    setMem(1, opcode.m1, input.removeFirst())

                    pointer += 2
                }

                is OpCode.Out -> {
                    val v1 = getMem(1, opcode.m1)
                    pointer += 2
                    return v1
                }

                is OpCode.Jit -> {
                    val v1 = getMem(1, opcode.m1)

                    pointer = if (v1 != 0) getMem(2, opcode.m2) else pointer + 3
                }

                is OpCode.Jif -> {
                    val v1 = getMem(1, opcode.m1)

                    pointer = if (v1 == 0) getMem(2, opcode.m2) else pointer + 3
                }

                is OpCode.Lt -> {
                    val v1 = getMem(1, opcode.m1)
                    val v2 = getMem(2, opcode.m2)

                    setMem(3, opcode.m3, if (v1 < v2) 1 else 0)
                    pointer += 4
                }

                is OpCode.Eq -> {
                    val v1 = getMem(1, opcode.m1)
                    val v2 = getMem(2, opcode.m2)

                    setMem(3, opcode.m3, if (v1 == v2) 1 else 0)
                    pointer += 4
                }

                OpCode.Exit -> {
                    return null
                }
            }
        }
    }

    fun setMem(
        offset: Int,
        mode: Mode,
        value: Int,
    ) {
        memory[getIndex(offset, mode)] = value
    }

    fun getMem(
        offset: Int,
        mode: Mode,
    ): Int = memory[getIndex(offset, mode)]

    fun getIndex(
        offset: Int,
        mode: Mode,
    ): Int =
        when (mode) {
            Mode.Imm -> pointer + offset
            Mode.Pos -> memory[pointer + offset]
        }
}
