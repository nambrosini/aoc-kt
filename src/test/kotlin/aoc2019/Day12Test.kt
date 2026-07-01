package aoc2019

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(2019, 12, 1))
        assertEquals(1940, Day12.part1(readExample(2019, 12, 1), 100))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(2019, 12, 2))
        assertEquals(4686774924, Day12.part2(readExample(2019, 12, 2)))
    }
}
