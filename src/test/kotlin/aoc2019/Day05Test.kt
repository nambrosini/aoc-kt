package aoc2019

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(2019, 5, 1))
        assertEquals(TODO(), Day05.part1(readExample(2019, 5, 1)))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(2019, 5, 2))
        assertEquals(TODO(), Day05.part2(readExample(2019, 5, 2)))
    }
}
