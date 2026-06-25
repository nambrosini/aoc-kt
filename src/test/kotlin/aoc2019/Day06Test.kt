package aoc2019

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(2019, 6, 1))
        assertEquals(42, Day06.part1(readExample(2019, 6, 1)))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(2019, 6, 2))
        assertEquals(4, Day06.part2(readExample(2019, 6, 2)))
    }
}
