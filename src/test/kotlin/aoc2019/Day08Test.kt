package aoc2019

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(2019, 8, 1))
        assertEquals(1, Day08.part1(readExample(2019, 8, 1), 3, 2))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(2019, 8, 2))
        assertEquals("\n #\n# ", Day08.part2(readExample(2019, 8, 2), 2, 2))
    }
}
