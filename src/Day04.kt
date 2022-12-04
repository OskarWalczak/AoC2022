fun main() {

    fun getRanges(line: String): Pair<IntRange, IntRange>{
        val nums = line.split(',', '-').map { Integer.parseInt(it) }
        return Pair((nums[0]..nums[1]), (nums[2]..nums[3]))
    }

    fun part1(input: List<String>): Int {
        var containCount: Int = 0
        input.forEach {
            val elfRanges = getRanges(it)
            val bigger = if (elfRanges.first.count() >= elfRanges.second.count()) elfRanges.first else elfRanges.second
            val smaller = if (bigger == elfRanges.first) elfRanges.second else elfRanges.first

            for(i in smaller){
                if(!bigger.contains(i)){
                    break
                }

                if(i == smaller.last)
                    containCount++
            }
        }

        return containCount
    }

    fun part2(input: List<String>): Int {
        var overlapCount: Int = 0
        input.forEach {
            val elfRanges = getRanges(it)

            for(i in elfRanges.first){
                if(elfRanges.second.contains(i)){
                    overlapCount++
                    break
                }
            }
        }
        return overlapCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
