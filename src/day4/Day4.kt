package day4

import readInput
import java.lang.Exception

fun main() {


    fun part1(input: List<String>): Int {
        var total = 0
        for (line in input) {
            println("loop")
            val (first, second) = line.split(",")
            val (firstFirst, firstSecond) = first.split("-").map(String::toInt)
            val (secondFirst, secondSecond) = second.split("-").map(String::toInt)
            println("$firstFirst, $firstSecond, $secondFirst, $secondSecond")
            if ((firstFirst >= secondFirst && firstSecond <= secondSecond) || (firstFirst <= secondFirst && firstSecond >= secondSecond)) {
                total++
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("day4/test")
    println(part1(testInput))
    check(part1(testInput) == 2)
    val input = readInput("day4/input")
    println(part1(input))
    println(part2(input))
}
