package aoc__YEAR__

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals

class Day__DAY__Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(__YEAR__, __DAY_UNPADDED__, 1))
        assertEquals(TODO(), Day__DAY__.part1(readExample(__YEAR__, __DAY_UNPADDED__, 1)))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(__YEAR__, __DAY_UNPADDED__, 2))
        assertEquals(TODO(), Day__DAY__.part2(readExample(__YEAR__, __DAY_UNPADDED__, 2)))
    }
}
