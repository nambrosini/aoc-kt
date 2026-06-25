package util

fun <T> Iterable<T>.permutations(): Sequence<List<T>> =
    sequence {
        val list = toList()
        if (list.size <= 1) {
            yield(list)
            return@sequence
        }
        for (element in list) {
            (list - element).permutations().forEach { yield(listOf(element) + it) }
        }
    }
