fun main() {

    fun readRegisterValues(input: List<String>): Map<Int, Int>{
        val registerValues: MutableMap<Int, Int> = HashMap()
        registerValues[0] = 1

        var currentCycleNumber = 1
        input.forEach { line ->
            if (line.trim() == "noop"){
                if(registerValues[currentCycleNumber] == null)
                    registerValues[currentCycleNumber] = registerValues[currentCycleNumber - 1]!!

                currentCycleNumber++
            }
            else if(line.startsWith("addx")){
                val numberToAdd = line.split(' ').last().toInt()

                if(registerValues[currentCycleNumber] == null)
                    registerValues[currentCycleNumber] = registerValues[currentCycleNumber - 1]!!

                currentCycleNumber++

                if(registerValues[currentCycleNumber] == null)
                    registerValues[currentCycleNumber] = registerValues[currentCycleNumber - 1]!!

                currentCycleNumber++

                registerValues[currentCycleNumber] = registerValues[currentCycleNumber-1]!! + numberToAdd
            }
        }

        return registerValues
    }

    fun part1(input: List<String>): Int {
        val registerValues = readRegisterValues(input)

        var cycleSums = 0
        for(i in 20 until registerValues.size step 40)
            cycleSums += registerValues[i]!! * i

        return cycleSums
    }

    fun part2(input: List<String>) {
        val registerValues = readRegisterValues(input)

        val crtVals: Array<CharArray> = Array(6){CharArray(40)}
        for(i in crtVals.indices){
            for( j in crtVals[i].indices){
                crtVals[i][j] = '.'
            }
        }

        registerValues.forEach { (index, value) ->
            val newIndex = (index-1)
            if(newIndex.mod(40) in value-1..value+1){
                crtVals[newIndex.div(40)][newIndex.mod(40)] = '#'
            }
        }

        for(i in crtVals.indices){
            for( j in crtVals[i].indices){
                print(crtVals[i][j])
            }
            println()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}
