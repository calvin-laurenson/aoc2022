package day1

import readInput

fun main() {
    fun getElfs(input: List<String>): List<Int> {
        val elfs: MutableList<Int> = mutableListOf()
        val elf: MutableList<Int> = mutableListOf()
        for (c in input) {
            if (c.isEmpty()) {
                elfs.add(elf.sum())
                elf.clear()
            } else {
                elf.add(c.toInt())
            }
        }
        return elfs.toList()
    }
    fun part1(input: List<String>): Int {
        val elfs = getElfs(input)
        return elfs.max()
    }

    fun part2(input: List<String>): Int {
        val elfs = getElfs(input)

        return elfs.sorted().takeLast(3).sum()
    }

    val input = readInput("day1/input")
    println(part1(input))
    println(part2(input))
}
