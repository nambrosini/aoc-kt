package util.math

import kotlin.math.absoluteValue

fun mcd(
    a: Int,
    b: Int,
): Int {
    var a = a.absoluteValue
    var b = b.absoluteValue

    if (a < b) {
        a = b.also { b = a }
    }

    return if (b == 0) a else mcd(b, a % b)
}

fun mcd(
    a: Long,
    b: Long,
): Long {
    var a = a.absoluteValue
    var b = b.absoluteValue

    if (a < b) {
        a = b.also { b = a }
    }

    return if (b == 0L) a else mcd(b, a % b)
}

fun lcm(
    a: Int,
    b: Int,
): Int =
    if (a == 0 || b == 0) {
        0
    } else {
        (a * b) / mcd(a, b)
    }

fun lcm(
    a: Long,
    b: Long,
): Long =
    if (a == 0L || b == 0L) {
        0
    } else {
        (a * b) / mcd(a, b)
    }
