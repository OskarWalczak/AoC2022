class Position(var x: Int, var y: Int)

class Knot(val name: String, val parent: Knot?){
    val position = Position(0,0)

    fun followParent(){
        if(parent == null)
            return
        if(parent.position.x !in this.position.x-1..this.position.x+1
            || parent.position.y !in this.position.y-1..this.position.y+1){
            if(parent.position.x > this.position.x){
                this.position.x++
            }
            else if( parent.position.x < this.position.x){
                this.position.x--
            }

            if(parent.position.y > this.position.y){
                this.position.y++
            }
            else if( parent.position.y < this.position.y){
                this.position.y--
            }
        }
    }
}

fun createRope(length: Int): List<Knot> {
    val rope: MutableList<Knot> = ArrayList()
    rope.add(Knot("H", null))

    for(i in 1 until length){
        val newKnot = Knot(i.toString(), rope[i-1])
        rope.add(newKnot)
    }

    return rope
}

fun tailVisitsForRopeLength(length: Int, instructions: List<String>): Int{
    val rope = createRope(length)
    val head = rope[0]
    val tail = rope.last()
    val tailHistory: MutableSet<Pair<Int, Int>> = HashSet()

    instructions.forEach { line ->
        val dir: Char = line.split(' ').first()[0]
        val dist: Int = line.split(' ').last().toInt()

        for(i in 1..dist){
            when(dir){
                'R' -> head.position.x++
                'L' -> head.position.x--
                'U' -> head.position.y++
                'D' -> head.position.y--
            }

            rope.forEach { knot ->
                knot.followParent()
            }

            tailHistory.add(Pair(tail.position.x, tail.position.y))
        }
    }

    return tailHistory.size
}

fun main() {
    
    fun part1(input: List<String>): Int {
        return tailVisitsForRopeLength(2, input)
    }

    fun part2(input: List<String>): Int {
        return tailVisitsForRopeLength(10, input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
