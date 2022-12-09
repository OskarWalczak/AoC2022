fun main() {

    val treeMatrix: Array<IntArray> = Array(99){IntArray(99)}
    val testMatrix: Array<IntArray> = Array(5){IntArray(5)}

    fun readLinesToMatrix(lines: List<String>, matrix: Array<IntArray>){
        lines.forEachIndexed { lineIndex, s ->
            s.forEachIndexed { stringIndex, c ->
                matrix[lineIndex][stringIndex] = c.digitToInt()
            }
        }
    }

    fun part1(matrix: Array<IntArray>): Int {
        var numOfVisible = 0

        fun checkVisibleLeft(i: Int, j: Int): Boolean{
            for(j1 in 0 until j){
                if(matrix[i][j1] >= matrix[i][j])
                    return false
            }
            return true
        }

        fun checkVisibleRight(i: Int, j: Int): Boolean{
            for(j1 in j+1 .. matrix[i].lastIndex){
                if(matrix[i][j1] >= matrix[i][j])
                    return false
            }
            return true
        }

        fun checkVisibleUp(i: Int, j: Int): Boolean{
            for(i1 in 0 until i){
                if(matrix[i1][j] >= matrix[i][j])
                    return false
            }
            return true
        }

        fun checkVisibleDown(i: Int, j: Int): Boolean{
            for(i1 in i+1 .. matrix.lastIndex){
                if(matrix[i1][j] >= matrix[i][j])
                    return false
            }
            return true
        }

        for(i in matrix.indices){
            for(j in matrix[i].indices){
                if(i == 0 || i == matrix.lastIndex || j == 0 || j == matrix[i].lastIndex) {
                    numOfVisible++
                    continue
                }

                if(checkVisibleLeft(i, j) || checkVisibleRight(i, j) || checkVisibleUp(i, j) || checkVisibleDown(i, j))
                    numOfVisible++
            }
        }

        return numOfVisible
    }

    fun part2(matrix: Array<IntArray>): Int {
        var maxScenicScore = 0

        fun visibleTreesLeft(i: Int, j: Int): Int{
            var numOfTrees = 0
            for(j1 in j-1 downTo 0){
                numOfTrees++
                if(matrix[i][j1] >= matrix[i][j])
                    break
            }
            return numOfTrees
        }

        fun visibleTreesRight(i: Int, j: Int): Int{
            var numOfTrees = 0
            for(j1 in j+1 .. matrix[i].lastIndex){
                numOfTrees++
                if(matrix[i][j1] >= matrix[i][j])
                    break
            }
            return numOfTrees
        }

        fun visibleTreesUp(i: Int, j: Int): Int{
            var numOfTrees = 0
            for(i1 in i-1 downTo 0){
                numOfTrees++
                if(matrix[i1][j] >= matrix[i][j])
                    break
            }
            return numOfTrees
        }

        fun visibleTreesDown(i: Int, j: Int): Int{
            var numOfTrees = 0
            for(i1 in i+1 .. matrix.lastIndex){
                numOfTrees++
                if(matrix[i1][j] >= matrix[i][j])
                    break
            }
            return numOfTrees
        }

        for(i in 1 until matrix.lastIndex){
            for(j in 1 until matrix[i].lastIndex){
                val currentScenicScore = visibleTreesLeft(i, j) * visibleTreesRight(i, j) * visibleTreesUp(i, j) * visibleTreesDown(i, j)
                if(currentScenicScore > maxScenicScore)
                    maxScenicScore = currentScenicScore
            }
        }

        return maxScenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    readLinesToMatrix(testInput, testMatrix)

    check(part1(testMatrix) == 21)

    val input = readInput("Day08")
    readLinesToMatrix(input, treeMatrix)
    println(part1(treeMatrix))
    println(part2(treeMatrix))
}
