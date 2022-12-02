fun main() {

    fun generateListOfSums(lines: List<String>): List<Int> {
        var currentSum: Int = 0

        val sums : MutableList<Int> = ArrayList<Int>()

        lines.forEach{line ->
            if(line.isEmpty()){
                sums.add(currentSum)
                currentSum = 0
            }
            else{
                val cals: Int = Integer.parseInt(line)
                currentSum += cals
            }
        }

        sums.sortDescending()
        return sums
    }

    fun part1(input: List<String>): Int {
        return generateListOfSums(input)[0]
    }

    fun part2(input: List<String>): Int {
        val sums = generateListOfSums(input)
        return sums[0] + sums[1] + sums[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
