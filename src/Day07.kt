
class TreeNode(val name: String) {
    var size: Int = 0
    var parent: TreeNode? = null
    val children: MutableList<TreeNode> = ArrayList()
    val files: MutableMap<String, Int> = HashMap()

    fun addToSize(num: Int){
        size += num
        parent?.addToSize(num)
    }
}

fun main() {

    var root = TreeNode("/")

    fun executeCommand(command: String, currentNode: TreeNode): TreeNode{
        var com = command.substring(2)
        var curr = currentNode

        if(com.startsWith("cd")){
            com = com.substring(3).trim()

            if(com == ".."){
                if(curr.parent != null)
                    curr = curr.parent!!
            }
            else if(com == "/"){
                curr = root
            }
            else {
                val child = curr.children.find { node -> node.name == com }
                if(child != null)
                    curr = child
            }
        }

        return curr
    }

    fun readInputIntoTree(input: List<String>){
        root = TreeNode("/")
        var currentNode = root
        input.forEach { line ->
            if(line.startsWith("$")) {
                currentNode = executeCommand(line, currentNode)
            }
            else if(line.startsWith("dir")){
                val dirname = line.substring(4).trim()
                val newNode = TreeNode(dirname)
                newNode.parent = currentNode
                currentNode.children.add(newNode)
            }
            else {
                val fileInfo = line.split(' ')
                if(fileInfo.first().toIntOrNull() != null){
                    currentNode.files[fileInfo.last()] = fileInfo.first().toInt()
                    currentNode.addToSize(fileInfo.first().toInt())
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        readInputIntoTree(input)

        val sizeLimit = 100000
        var sizeSum = 0

        fun checkChildren(node: TreeNode){
            if(node.size <= sizeLimit)
                sizeSum += node.size

            node.children.forEach { child ->
                checkChildren(child)
            }
        }

        checkChildren(root)

        return sizeSum
    }

    fun part2(input: List<String>): Int {

        val spaceNeeded = 30000000 - (70000000 - root.size)
        var currentSize = root.size

        fun checkChildren(node: TreeNode){
            if(node.size in spaceNeeded until currentSize)
                currentSize = node.size

            node.children.forEach { child ->
                checkChildren(child)
            }
        }

        checkChildren(root)

        return currentSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
