package aoc2019

import org.junit.jupiter.api.Assumptions.assumeTrue
import util.exampleExists
import util.readExample
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day04Test {
    @Test
    fun part1() {
        assumeTrue(exampleExists(2019, 4, 1))
        assertEquals(TODO(), Day04.part1(readExample(2019, 4, 1)))
    }

    @Test
    fun part2() {
        assumeTrue(exampleExists(2019, 4, 2))
        assertEquals(TODO(), Day04.part2(readExample(2019, 4, 2)))
    }

    @Test
    fun testCondition() {
        assertTrue(Day04.isValid(111111))
        assertFalse(Day04.isValid(223450))
        assertFalse(Day04.isValid(123789))
    }

    @Test
    fun testConditionPart2() {
        assertTrue(Day04.isValidPart2(112233))
        assertFalse(Day04.isValidPart2(123444))
        assertFalse(Day04.isValidPart2(111122))
    }
}
