package aoc2019

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(2019, 3, 1))
        assertEquals(135, Day03.part1(readExample(2019, 3, 1)))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(2019, 3, 2))
        assertEquals(TODO(), Day03.part2(readExample(2019, 3, 2)))
    }
}
