package day5

import readInput

fun main() {

    fun parseCrateColumns(row: String): List<Char?> {
        return row.chunked(4).map { if (it.isBlank()) null else it[1] }
    }

    fun parseCrateColumns(rows: List<String>): Map<Int, MutableList<Char>> {
        return buildMap {
            for (row in rows) {
                val parsedRow = parseCrateColumns(row)
                println(parsedRow)
                parsedRow.forEachIndexed { i, c ->
//                    println("$i, $c")
                    if (c != null) {
                        if (!contains(i + 1)) {
                            put(i + 1, mutableListOf())
                        }
//                        println("adding $c to ${i+1}")
                        get(i+1)!!.add(0, c)
                    }
                }
            }
        }
    }

    fun part1(input: List<String>): String {
        val splitIndex = input.indexOfFirst { it.isEmpty() }
        val startingRaw = input.slice(0 until splitIndex - 1)
        val labels = input[splitIndex - 1]
//        println("labels: $labels")
        val boxes: Map<Int, MutableList<Char>> = parseCrateColumns(startingRaw)
        println(boxes)

        val commands = input.takeLastWhile { it.isNotEmpty() }
        for (command in commands) {
            val (amount, from, to) = Regex("""move (\d+) from (\d+) to (\d+)""").find(command)!!.groups.toList().takeLast(3).map { it!!.value.toInt()}
            val fromList = boxes[from]!!
            val toList = boxes[to]!!
            val chars = fromList.takeLast(amount)
            chars.forEach { _ -> fromList.removeLast() }
            chars.reversed().forEach { toList.add(it) }
        }
        println(boxes)
        return boxes.toList().sortedBy { it.first }.map { it.second.last() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val splitIndex = input.indexOfFirst { it.isEmpty() }
        val startingRaw = input.slice(0 until splitIndex - 1)
        val labels = input[splitIndex - 1]
//        println("labels: $labels")
        val boxes: Map<Int, MutableList<Char>> = parseCrateColumns(startingRaw)
        println(boxes)

        val commands = input.takeLastWhile { it.isNotEmpty() }
        for (command in commands) {
            val (amount, from, to) = Regex("""move (\d+) from (\d+) to (\d+)""").find(command)!!.groups.toList().takeLast(3).map { it!!.value.toInt()}
            val fromList = boxes[from]!!
            val toList = boxes[to]!!
            val chars = fromList.takeLast(amount)
            chars.forEach { _ -> fromList.removeLast() }
            chars.forEach { toList.add(it) }
        }
        println(boxes)
        return boxes.toList().sortedBy { it.first }.map { it.second.last() }.joinToString("")

    }

    val testInput = readInput("day5/test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")
    val input = readInput("day5/input")
    println(part1(input))
    println(part2(input))
}
