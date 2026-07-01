package aoc2019

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(2019, 10, 1))
        assertEquals(210, Day10.part1(readExample(2019, 10, 1)))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(2019, 10, 2))
        assertEquals(802, Day10.part2(readExample(2019, 10, 2)))
    }
}
