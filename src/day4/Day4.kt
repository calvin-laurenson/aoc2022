package day4

import readInput
import java.lang.Exception

fun main() {


    fun part1(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val (first, second) = line.split(",")
            val (firstFirst, firstSecond) = first.split("-").map(String::toInt)
            val (secondFirst, secondSecond) = second.split("-").map(String::toInt)
            if ((firstFirst >= secondFirst && firstSecond <= secondSecond) || (firstFirst <= secondFirst && firstSecond >= secondSecond)) {
                total++
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val (first, second) = line.split(",")
            val (firstFirst, firstSecond) = first.split("-").map(String::toInt)
            val (secondFirst, secondSecond) = second.split("-").map(String::toInt)
            if ((firstFirst..firstSecond).toList().intersect(   (secondFirst..secondSecond).toList().toSet()).isNotEmpty()) {
                total++
                println("Yes: $firstFirst, $firstSecond, $secondFirst, $secondSecond")
            } else {
                println("No: $firstFirst, $firstSecond, $secondFirst, $secondSecond")
            }
        }
        return total
    }

    val testInput = readInput("day4/test")
    check(part1(testInput) == 2)
    val input = readInput("day4/input")
    println(part1(input))
    println(part2(input))
}
