fun main() {

    fun getItemPriority(item: Char) : Int {
        val charVal = item.code
        if (charVal in 'a'.code .. 'z'.code)
            return charVal - 'a'.code + 1

        if(charVal in 'A'.code .. 'Z'.code)
            return charVal - 'A'.code + 27

        return 0
    }

    fun part1(input: List<String>): Int {
        var sum: Int = 0

        input.forEach { line ->
            val comp1: String = line.substring(0, line.length/2)
            val comp2: String = line.substring(line.length/2)

            for(c: Char in comp1){
                if(comp2.contains(c)){
                    sum += getItemPriority(c)
                    break
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum: Int = 0
        for(i: Int in input.indices step 3){
            val ruck1: String = input[i]
            val ruck2: String = input[i+1]
            val ruck3: String = input[i+2]

            for(c: Char in ruck1){
                if(ruck2.contains(c) && ruck3.contains(c)){
                    sum += getItemPriority(c)
                    break
                }
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
