package day7

import readInput
import java.lang.Exception
import kotlin.math.absoluteValue

fun getAllFolders(folder: Item.Folder): List<Item.Folder> {
    val folders = mutableListOf<Item.Folder>(folder)
    for (item in folder.items) {
        if (item is Item.Folder) {
            val subFolders = getAllFolders(item)
            folders.addAll(subFolders)
            folders.add(item)
        }
    }
    return folders
}

fun getAllFiles(folder: Item.Folder): List<Item.File> {
    val files = mutableListOf<Item.File>()
    for (item in folder.items) {
//        println(item)
        when (item) {
            is Item.Folder -> {
                val subFolders = getAllFiles(item)
                files.addAll(subFolders)
            }
            is Item.File -> {
                files.add(item)
            }
        }
    }
    return files
}
sealed class Item {
    data class File(val name: String, val size: Int) : Item()
    data class Folder(val name: String, val items: MutableList<Item>) : Item() {
        fun size(): Int {
            return getAllFiles(this).sumOf { it.size }
        }
    }

    companion object {
        fun parse(item: String): Item {
            val split = item.split(" ")
            return when {
                split[0] == "dir" -> Folder(split[1], mutableListOf())
                split[0].toIntOrNull() != null -> File(split[1], split[0].toInt())
                else -> throw Exception("invalid item")
            }
        }
    }
}

sealed class Command {
    data class Cd(val directory: String) : Command()
    object Ls : Command()

    companion object {
        fun parse(command: String): Command {
            val split = command.split(" ")
            return when (split[0]) {
                "cd" -> Cd(split[1])
                "ls" -> Ls
                else -> throw Exception("Invalid command")
            }
        }
    }
}

fun main() {



    f@ fun part1(input: List<String>): Int {
        val root = Item.Folder("/", mutableListOf())
        var pwd = root
        var currentCommand: Command? = null
        for (line in input) {
            if (line.startsWith("$")) {
                // Is command
                val command = Command.parse(line.drop(2))
//                println("root: $root")
//                println("pwd: $pwd")
//                println("command: $command")
                if (command is Command.Cd) {
                    when (command.directory) {
                        ".." -> {
                            pwd = getAllFolders(root).find { it.items.contains(pwd) }!!
                        }
                        "/" -> pwd = root
                        else -> {
                            val newFolder = Item.Folder(command.directory, mutableListOf())
                            pwd.items.add(newFolder)
                            pwd = newFolder
                        }
                    }
                }
                currentCommand = command
            } else {
                // Is output
                if (currentCommand is Command.Ls) {
                    val item = Item.parse(line)
//                    println("adding: $item to $pwd")
                    pwd.items.add(item)
                }
            }
        }

        return getAllFolders(root).toSet().map { it.size() }.filter { it <= 100_000 }.sum()
    }

    f@ fun part2(input: List<String>): Int {
        val root = Item.Folder("/", mutableListOf())
        var pwd = root
        var currentCommand: Command? = null
        for (line in input) {
            if (line.startsWith("$")) {
                // Is command
                val command = Command.parse(line.drop(2))
//                println("root: $root")
//                println("pwd: $pwd")
//                println("command: $command")
                if (command is Command.Cd) {
                    when (command.directory) {
                        ".." -> {
                            pwd = getAllFolders(root).find { it.items.contains(pwd) }!!
                        }
                        "/" -> pwd = root
                        else -> {
                            val newFolder = Item.Folder(command.directory, mutableListOf())
                            pwd.items.add(newFolder)
                            pwd = newFolder
                        }
                    }
                }
                currentCommand = command
            } else {
                // Is output
                if (currentCommand is Command.Ls) {
                    val item = Item.parse(line)
//                    println("adding: $item to $pwd")
                    pwd.items.add(item)
                }
            }
        }
        val allFolders = getAllFolders(root)
        val rootSize = root.size()
        val needToFree = (40_000_000 - rootSize).absoluteValue
        return allFolders.toSet().map { it.size() }.filter { it > needToFree }.sorted()[0]

    }

    val testInput = readInput("day7/test")
//    println(part1(testInput))
    check(part1(testInput) == 95437)
//    check(part2(testInput) == 26)
    val input = readInput("day7/input")
    println(part1(input))
    println(part2(input))
}
