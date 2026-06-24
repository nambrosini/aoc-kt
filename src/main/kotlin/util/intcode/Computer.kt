package util.intcode

class Computer(val memory: MutableList<Int>) {
    var pointer: Int = 0

    fun run(a: Int, b: Int) {
        memory[1] = a
        memory[2] = b

        while (true) {
            val opcode = memory[pointer].toOpcode()
            when (opcode) {
                OpCode.Add -> {
                    val v1 = getMem(1)
                    val v2 = getMem(2)

                    setMem(3, v1 + v2)
                    pointer += 4
                }

                OpCode.Mult -> {
                    val v1 = getMem(1)
                    val v2 = getMem(2)

                    setMem(3, v1 * v2)
                    pointer += 4
                }

                OpCode.Exit -> {
                    return
                }
            }
        }
    }

    fun setMem(offset: Int, value: Int) {
        memory[memory[pointer + offset]] = value
    }

    fun getMem(offset: Int): Int {
        return memory[memory[pointer + offset]]
    }
}
