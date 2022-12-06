package day6

import readInput
import java.lang.Exception

fun main() {


    f@ fun part1(input: List<String>): Int {
        val str = input[0]
        val chars = mutableListOf<Char>()
        str.forEachIndexed { i, c ->
            chars.add(c)
            if (chars.toSet().size == 4) {
                return@f i + 1
            }
            if (chars.size == 4) {
                chars.removeFirst()
            }
        }
        throw Exception("no seq")
    }

    f@ fun part2(input: List<String>): Int {
        val str = input[0]
        val chars = mutableListOf<Char>()
        str.forEachIndexed { i, c ->
            chars.add(c)
            if (chars.toSet().size == 14) {
                return@f i + 1
            }
            if (chars.size == 14) {
                chars.removeFirst()
            }
        }
        throw Exception("no seq")
    }

    val testInput = readInput("day6/test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 26)
    val input = readInput("day6/input")
    println(part1(input))
    println(part2(input))
}
