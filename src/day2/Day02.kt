package day2

import readInput
import java.lang.Exception

fun main() {

    fun part1(input: List<String>): Int {
        var score = 0
        for (match in input) {
            val (me, them) = match.split(" ")
            println("me: $me")
            println("them: $them")
            when (them) {
                "X" ->  {
                    score += 1
                    score += when (me) {
                        "A" -> 3
                        "B" -> 0
                        "C" -> 6
                        else -> throw Exception("Invalid type: $me")
                    }
                }
                "Y" -> {
                    score += 2
                    score += when (me) {
                        "A" -> 6
                        "B" -> 3
                        "C" -> 0
                        else -> throw Exception("Invalid type: $me")
                    }
                }
                "Z" -> {
                    score += 3
                    score += when (me) {
                        "A" -> 0
                        "B" -> 6
                        "C" -> 3
                        else -> throw Exception("Invalid type: $me")
                    }
                }
                else -> throw Exception("Invalid type: $them")
            }
        }
        return score
    }

    fun part2(input: List<String>): Int {
        return 0
    }
    val testInput = readInput("day2/test")
    check(part1(testInput) == 15)
    val input = readInput("day2/input")
    println(part1(input))
    println(part2(input))
}
