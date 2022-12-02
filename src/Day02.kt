fun main() {

    val symbolValueMap: Map<Char, Int> = mapOf('A' to 1, 'B' to 2, 'C' to 3, 'X' to 1, 'Y' to 2, 'Z' to 3)

    fun calculateOutcomeScore(theirSymbol: Char, mySymbol: Char): Int {
        if(symbolValueMap[theirSymbol] == symbolValueMap[mySymbol])
            return symbolValueMap[mySymbol]!! + 3

        val diff = symbolValueMap[theirSymbol]?.minus(symbolValueMap[mySymbol]!!)
        return if(diff == -1 || diff == 2)
            symbolValueMap[mySymbol]!! + 6
        else
            symbolValueMap[mySymbol]!!
    }

    fun part1(input: List<String>): Int {
        var score: Int = 0

        input.forEach{input ->
            score += calculateOutcomeScore(input[0], input[2])
        }

        return score
    }

    fun part2(input: List<String>): Int {
        var score: Int = 0

        input.forEach{line ->
            if(line[2] == 'X'){
                if(line[0] == 'A')
                    score += calculateOutcomeScore(line[0], 'C')
                if(line[0] == 'B')
                    score += calculateOutcomeScore(line[0], 'A')
                if(line[0] == 'C')
                    score += calculateOutcomeScore(line[0], 'B')
            }
            else if(line[2] == 'Y'){
                score += calculateOutcomeScore(line[0], line[0])
            }
            else{
                if(line[0] == 'A')
                    score += calculateOutcomeScore(line[0], 'B')
                if(line[0] == 'B')
                    score += calculateOutcomeScore(line[0], 'C')
                if(line[0] == 'C')
                    score += calculateOutcomeScore(line[0], 'A')
            }
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
