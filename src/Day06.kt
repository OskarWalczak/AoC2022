import java.util.*

fun main() {

    fun allElementsUnique(col: Collection<Char>): Boolean {
        for(i in col.indices){
            for(j in i+1 until col.size){
                if(col.elementAt(i) == col.elementAt(j)){
                    return false
                }
            }
        }

        return true
    }

    fun part1(input: List<String>): Int {
        val signal: String = input[0]

        val chars: Queue<Char> = LinkedList()
        for(i in signal.indices){
            val c: Char = signal[i]
            if(chars.size == 4){
                chars.poll()
            }

            chars.offer(c)

            if(chars.size == 4){
                if(allElementsUnique(chars))
                    return i+1
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val signal: String = input[0]

        val chars: Queue<Char> = LinkedList()
        for(i in signal.indices){
            val c: Char = signal[i]
            if(chars.size == 14){
                chars.poll()
            }

            chars.offer(c)

            if(chars.size == 14){
                if(allElementsUnique(chars))
                    return i+1
            }
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 10)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))

}
