package aoc2019

import util.readInput

object Day02 {
    fun part1(input: String): Int {
        val pc = Pc(input.split(',').map { it.toInt() }.toMutableList())
        pc.run(12, 2)
        return pc.memory[0]
    }

    fun part2(input: String): Int {
        val memory = input.split(',').map { it.toInt() }.toMutableList()

        for (a in 0..100) {
            for (b in 0..100) {
                val pc = Pc(memory.toMutableList())
                pc.run(a, b)

                if (pc.memory[0] == 19690720) {
                    return 100 * a + b
                }
            }
        }

        return -1
    }
}

class Pc(
    val memory: MutableList<Int>,
) {
    var pointer: Int = 0

    fun run(
        a: Int,
        b: Int,
    ) {
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

    fun setMem(
        offset: Int,
        value: Int,
    ) {
        memory[memory[pointer + offset]] = value
    }

    fun getMem(offset: Int): Int = memory[memory[pointer + offset]]
}

enum class OpCode(
    val code: Int,
) {
    Add(1),
    Mult(2),
    Exit(99),
}

fun Int.toOpcode(): OpCode =
    when (this) {
        1 -> OpCode.Add
        2 -> OpCode.Mult
        99 -> OpCode.Exit
        else -> throw IllegalArgumentException("No Opcode for value $this")
    }

fun main() {
    val input = readInput(2019, 2)
    println("Part 1: ${Day02.part1(input)}")
    println("Part 2: ${Day02.part2(input)}")
}
