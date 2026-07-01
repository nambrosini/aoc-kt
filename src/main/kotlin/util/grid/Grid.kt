import util.loc.Pos

data class Grid<T>(
    val grid: Array<Array<T>>,
) {
    companion object {
        inline fun <reified T> fromSize(
            width: Int,
            height: Int,
            default: T,
        ): Grid<T> {
            val grid = Array(height) { Array(width) { default } }
            return Grid(grid)
        }
    }

    fun countNeighbours(cell: T): Grid<Int> {
        val grid = Grid.fromSize(this.grid[0].size, this.grid.size, 9)
        for ((x, row) in this.grid.withIndex()) {
            for ((y, c) in row.withIndex()) {
                if (c != cell) {
                    continue
                }
                grid.grid[x][y] = this.neighboursForCell(x, y, cell)
            }
        }
        return grid
    }

    fun neighboursForCell(
        x: Int,
        y: Int,
        target: T,
    ): Int {
        val rows = grid.size
        val cols = grid[0].size
        val xMin = (x - 1).coerceAtLeast(0)
        val xMax = (x + 1).coerceAtMost(rows - 1)
        val yMin = (y - 1).coerceAtLeast(0)
        val yMax = (y + 1).coerceAtMost(cols - 1)
        var count = 0
        for (i in xMin..xMax) {
            for (j in yMin..yMax) {
                if (i == x && j == y) continue
                if (grid[i][j] == target) count++
            }
        }
        return count
    }

    fun find(value: T): Pos? {
        for ((i, row) in this.grid.withIndex()) {
            for ((j, cell) in this.grid.withIndex()) {
                if (cell == value) {
                    return Pos(i, j)
                }
            }
        }

        return null
    }

    fun findAll(value: T): List<Pos> {
        var found = mutableListOf<Pos>()

        for ((y, row) in this.grid.withIndex()) {
            for ((x, cell) in row.withIndex()) {
                if (cell == value) {
                    found.add(Pos(x, y))
                }
            }
        }

        return found
    }

    fun isInbound(pos: Pos): Boolean = pos.x >= 0 && pos.x < this.grid.size && pos.y >= 0 && pos.y < this.grid[0].size

    fun get(pos: Pos): T? {
        if (!isInbound(pos)) {
            return null
        }

        return this.grid[pos.y.toInt()][pos.x.toInt()]
    }

    override fun toString(): String {
        val builder = StringBuilder()

        for (row in grid) {
            for (cell in row) {
                builder.append(cell)
            }
            builder.append("\n")
        }

        return builder.toString()
    }
}

fun String.toGrid(): Grid<Char> {
    val grid = this.lines().map { it.toCharArray().toTypedArray() }.toTypedArray()

    return Grid<Char>(grid)
}
