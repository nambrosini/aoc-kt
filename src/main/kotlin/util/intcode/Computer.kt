package util.intcode

fun parseMemory(input: String): LongArray =
    input
        .trim()
        .split(",")
        .map { it.toLong() }
        .toLongArray()

class Computer(
    var memory: LongArray,
) {
    var pointer: Int = 0
    var relativeBase: Int = 0

    fun run(input: MutableList<Long>): Long? {
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

                    pointer = if (v1 != 0L) getMem(2, opcode.m2).toInt() else pointer + 3
                }

                is OpCode.Jif -> {
                    val v1 = getMem(1, opcode.m1)

                    pointer = if (v1 == 0L) getMem(2, opcode.m2).toInt() else pointer + 3
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

                is OpCode.Arb -> {
                    val v1 = getMem(1, opcode.m1)

                    relativeBase += v1.toInt()
                    pointer += 2
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
        value: Long,
    ) {
        val index = getIndex(offset, mode)
        memory[index] = value
    }

    fun getMem(
        offset: Int,
        mode: Mode,
    ): Long = memory[getIndex(offset, mode)]

    fun getIndex(
        offset: Int,
        mode: Mode,
    ): Int {
        val index =
            when (mode) {
                Mode.Imm -> pointer + offset
                Mode.Pos -> memory[pointer + offset].toInt()
                Mode.Rel -> memory[pointer + offset].toInt() + relativeBase
            }

        if (memory.size <= index) {
            this.memory = this.memory.copyOf(index + 1)
        }

        return index
    }
}
