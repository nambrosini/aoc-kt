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
