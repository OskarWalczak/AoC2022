import java.util.*
import kotlin.collections.ArrayList

fun main() {

    fun readStacks(input: List<String>): MutableList<Stack<Char>> {
        val stacks: MutableList<Stack<Char>> = ArrayList()
        var stackNumsLineIndex: Int = 0
        for(line in input.withIndex()){
            if(line.value.matches(Regex("[0-9 ]+"))){
                stackNumsLineIndex = line.index
                break
            }
        }

        stacks.add(Stack())
        for(i: Int? in input[stackNumsLineIndex].split(' ').map { it.toIntOrNull() }){
            if(i != null)
                stacks.add(Stack())
        }

        for(i: Int in stackNumsLineIndex-1 downTo 0){
            val line: String = input[i]
            var stackNum = 1
            for(j: Int in 1..line.length step 4){
                if(line[j] in 'A'..'Z'){
                    stacks[stackNum].push(line[j])
                }
                stackNum++
            }
        }

        return stacks
    }

    fun readInstructions(input: List<String>): MutableList<Triple<Int, Int, Int>> {
        val instructions: MutableList<Triple<Int, Int, Int>> = ArrayList()

        input.forEach { line ->
            if(line.startsWith("move")){
                val nums = line.split(' ').map { it.toIntOrNull() }
                val notNulls = nums.filterNotNull()
                instructions.add(Triple(notNulls[0], notNulls[1], notNulls[2]))
            }
        }

        return instructions
    }

    fun applyInstructionsToStacks(instructions: MutableList<Triple<Int, Int, Int>>, stacks: MutableList<Stack<Char>>): MutableList<Stack<Char>>{
        instructions.forEach{ instruction ->
            for(i in 1..instruction.first){
                stacks[instruction.third].push(stacks[instruction.second].pop())
            }
        }

        return stacks
    }

    fun applyInstructionsToNotStacks(instructions: MutableList<Triple<Int, Int, Int>>, stacks: MutableList<Stack<Char>>): MutableList<Stack<Char>>{
        instructions.forEach{ instruction ->
            val tempStack: Stack<Char> = Stack()
            for(i in 1..instruction.first){
                tempStack.push(stacks[instruction.second].pop())
            }

            for(i in 0 until tempStack.size){
                stacks[instruction.third].push(tempStack.pop())
            }
        }

        return stacks
    }

    fun part1(input: List<String>): String {
        val stacks = applyInstructionsToStacks(readInstructions(input), readStacks(input))

        var tops: String = ""
        for(i in 1 until stacks.size){
            tops += stacks[i].peek()
        }
        return tops
    }

    fun part2(input: List<String>): String {
        val stacks = applyInstructionsToNotStacks(readInstructions(input), readStacks(input))

        var tops: String = ""
        for(i in 1 until stacks.size){
            tops += stacks[i].peek()
        }
        return tops
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
//
    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))

}
