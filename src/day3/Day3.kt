package day2

import readInput
import java.lang.Exception

fun main() {
    val priority = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toList()

    fun commonChars(first: String, second: String): List<Char> {
        val chars = mutableListOf<Char>()
        for (char in first) {
            if (second.contains(char)) {
                chars.add(char)
            }
        }
        return chars
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val first = line.take(line.length / 2)
            val second = line.takeLast(line.length / 2)
            sum += priority.indexOf(commonChars(first, second)[0]) + 1
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (group in input.chunked(3)) {
            val (first, second, third) = group
            val common1 = commonChars(first, second)
            val common2 = commonChars(first, third)
            val common3 = commonChars(second, third)
            loop@ for (l in listOf(common1, common2, common3).sortedBy {
                it.size
            }.reversed()) {
                for (char in l) {
                    if (common2.contains(char) && common3.contains(char)) {
                        sum += priority.indexOf(char) + 1
                        break@loop
                    }
                }
            }
        }
        return sum
    }

    val testInput = readInput("day3/test")
    check(part1(testInput) == 157)
    val input = readInput("day3/input")
    println(part1(input))
    println(part2(input))
}
