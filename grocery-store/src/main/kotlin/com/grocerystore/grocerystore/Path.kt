package com.grocerystore.grocerystore


import com.grocerystore.grocerystore.models.StoreMapping
import java.util.*

class Node(var point: StoreMapping, var dist: Int, var parent: Node?)

class Path(val rows: Int, val columns: Int) {

//object Path {
    var rowNum = intArrayOf(-1, 0, 0, 1)
    var colNum = intArrayOf(0, -1, 1, 0)
//    var rows = 9
//    val columns =8

    private fun getCell(row: Int, column: Int, store: List<StoreMapping>) = store.first { elem -> elem.column == column && elem.row == row }

    private fun isValid(row: Int, column: Int) :Boolean{
        return row in 0 until rows && column in 0 until columns
    }
    private fun isPath(row: Int, column: Int, store:List<StoreMapping>):Boolean {
     return getCell(row, column , store).value == 0
    }

    private fun getShortestPathPay(start: StoreMapping, store: List<StoreMapping>): Node?{
        val isVisited = Array(rows) { BooleanArray(columns) }

        isVisited[start.row][start.column] = true
        val queue = LinkedList<Node>()

        val s = Node(start, 0, null)
        queue.add(s)
        while (!queue.isEmpty()){
            val current : Node = queue.first()
            val point = current.point
            queue.remove()
            for (i in 0..3) {
                val row = point.row + rowNum[i]
                val col = point.column + colNum[i]

                if(isValid(row, col) && !isVisited[row][col]){
                    val cellNew = getCell(row, col, store)
                    if(cellNew.value == -4) return Node(cellNew, current.dist+1, current)
                    if(isPath(row, col, store) ) {
                        isVisited[row][col] = true
                        val cell = Node(cellNew, current.dist + 1, current)
                        queue.add(cell)
                    }
                }
            }
        }

        return null

    }
    private fun getShortestPath(start: StoreMapping, end: StoreMapping, store: List<StoreMapping>): Node?
    {

        val isVisited = Array(rows) { BooleanArray(columns) }

        isVisited[start.row][start.column] = true
        val queue = LinkedList<Node>()

        val s = Node(start, 0, null)
        queue.add(s)
        while (!queue.isEmpty()){
            val current : Node = queue.first()
            val point = current.point

            if(point == end) return current

            queue.remove()
            for (i in 0..3) {
                val row = point.row + rowNum[i]
                val col = point.column + colNum[i]

                if(isValid(row, col) && !isVisited[row][col]){
                    val cellNew = getCell(row, col, store)
                    if(cellNew == end) return current
                    if(isPath(row, col, store) ) {
                        isVisited[row][col] = true
                        val cell = Node(cellNew, current.dist + 1, current)
                        queue.add(cell)
                    }
                }
            }
        }

        return null


    }

    fun getPath(store: List<StoreMapping>, start: StoreMapping, points: List<StoreMapping>): List<StoreMapping> {
        val res: MutableList<Node> = mutableListOf()
        var startPoint = start
        var min = Int.MAX_VALUE
        var minNode : Node? = null
        var newStart : StoreMapping? = null
        val allPoints : MutableList<StoreMapping> = mutableListOf()
        points.forEach{ allPoints.add(it)}
        while(allPoints.isNotEmpty()){

            for (point in allPoints) {
                val node = getShortestPath(startPoint, point, store)
                if(node?.dist!! < min){
                    minNode = node
                    min= node.dist
                    newStart = point
                }
            }
            if (minNode != null) {
                res.add(minNode)
                allPoints.remove(newStart)
                startPoint = newStart!!
                min=Int.MAX_VALUE

            }

        }

        val result : MutableList<StoreMapping> = mutableListOf()
        result.add(start)

        for(node in res) {

            val partialResult : MutableList<StoreMapping> = mutableListOf()

            var n = node
            while (true) {
                if (n.parent == null)
                    break
                partialResult.add(n.point)

                n = n.parent!!
            }
            partialResult.reverse()
            result.addAll(partialResult)
        }

        var last = result.last()
        var lastNode = getShortestPathPay(last, store)
        val partialResult : MutableList<StoreMapping> = mutableListOf()

        while (true) {
            if (lastNode?.parent == null)
                break
            partialResult.add(lastNode.point)

            lastNode = lastNode.parent!!
        }
        partialResult.reverse()
        result.addAll(partialResult)
//        print("$partialResult\n")
        return result

    }

//    private fun printPath(node: Node?): Int {
//        if (node == null) {
//            return 0
//        }
//        val len = printPath(node.parent)
//        print("${node.point.value }  row: ${node.point.row }  column: ${node.point.column }\n")
//        return len + 1
//    }

//
//        @JvmStatic
//        fun main(args: Array<String>) {
//
//
//
//            val mat = listOf(StoreMapping(1, 0,0,-1), StoreMapping(2, 0,1,-1), StoreMapping(3, 0,2,-1), StoreMapping(4, 0,3,-1), StoreMapping(5, 0,4,-1),
//                    StoreMapping(6, 0,5,-1),StoreMapping(7, 0,6,-1), StoreMapping(8, 0,7,-1), StoreMapping(9, 0,8,-1),
//                    StoreMapping(10, 1,0,-1),StoreMapping(11, 1,1,5), StoreMapping(12, 1,2,4), StoreMapping(13, 1,3,4)
//                    , StoreMapping(14, 1,4,3), StoreMapping(15, 1,5,2), StoreMapping(16, 1,6,2),StoreMapping(17, 1,7,1)
//                    , StoreMapping(18, 1,8,-1),StoreMapping(20, 2,0,-1),StoreMapping(21, 2,1,0), StoreMapping(22, 2,2,0),
//                    StoreMapping(23, 2,3,0), StoreMapping(24, 2,4,0), StoreMapping(25, 2,5,0), StoreMapping(26, 2,6,0)
//                    ,StoreMapping(27, 2,7,0), StoreMapping(28, 2,8,-2),StoreMapping(30, 3,0,-1),StoreMapping(31, 3,1,6),
//                    StoreMapping(32, 3,2,0), StoreMapping(33, 3,3,3), StoreMapping(34, 3,4,3), StoreMapping(35, 3,5,0),
//                    StoreMapping(36, 3,6,-1),StoreMapping(37, 3,7,0), StoreMapping(38, 3,8,-3),StoreMapping(40, 4,0,-1)
//                    ,StoreMapping(41, 4,1,7), StoreMapping(42, 4,2,0), StoreMapping(43, 4,3,4), StoreMapping(44, 4,4,5)
//                    , StoreMapping(45, 4,5,0), StoreMapping(46, 4,6,-4),StoreMapping(47, 4,7,0), StoreMapping(48, 4,8,-1)
//                    ,StoreMapping(50, 5,0,-1),StoreMapping(51, 5,1,7), StoreMapping(52, 5,2,0), StoreMapping(53, 5,3,0)
//                    , StoreMapping(54, 5,4,0), StoreMapping(55, 5,5,0), StoreMapping(56, 5,6,-4),StoreMapping(57, 5,7,0)
//                    , StoreMapping(58, 5,8,-1),StoreMapping(60, 6,0,-1),StoreMapping(61, 6,1,8), StoreMapping(62, 6,2,0)
//                    , StoreMapping(63, 6,3,4), StoreMapping(64, 6,4,3), StoreMapping(65, 6,5,0), StoreMapping(66, 6,6,-4)
//                    ,StoreMapping(67, 6,7,0), StoreMapping(68, 6,8,-1),StoreMapping(70, 7,0,-1),StoreMapping(71, 7,1,-1)
//                    , StoreMapping(72, 7,2,-1), StoreMapping(73, 7,3,-1), StoreMapping(74, 7,4,-1), StoreMapping(75, 7,5,-1)
//                    , StoreMapping(76, 7,6,-1),StoreMapping(77, 7,7,-1), StoreMapping(78, 7,8,-1)
//
//            )
////
////
////                    arrayOf(intArrayOf(1, 0, 1, 1, 1, 1, 0, 1, 1, 1), intArrayOf(1, 0, 1, 0, 1, 1, 1, 0, 1, 1), intArrayOf(1, 1, 1, 0, 1, 1, 0, 1, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 0, 1), intArrayOf(1, 1, 1, 0, 1, 1, 1, 0, 1, 0), intArrayOf(1, 0, 1, 1, 1, 1, 0, 1, 0, 0), intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(1, 0, 1, 1, 1, 1, 0, 1, 1, 1), intArrayOf(1, 1, 0, 0, 0, 0, 1, 0, 0, 1))
//            val source = StoreMapping(28, 2,8,-2)
//            val dest = StoreMapping(66, 6,6,-4)
////            val dist = getShortestPath(source, dest, mat)
//
//            val list = listOf<StoreMapping>(StoreMapping(64, 6,+4,3),StoreMapping(41, 4,1,7), StoreMapping(14, 1,4,3) )
//            var valResult = getPath(mat, source, dest, list)
////            val result : MutableList<StoreMapping> = mutableListOf()
////            result.add(source)
////
////            for(node in valResult) {
////
////                val partialResult : MutableList<StoreMapping> = mutableListOf()
////
////                var n = node
////                while (true) {
////
////                    if (n.parent == null)
////                        break
////                    partialResult.add(n.point)
////                    n = n.parent!!
////                }
////                partialResult.reverse()
////                result.addAll(partialResult)
////            }
////                print("\n")
//                valResult.forEach{ print("${it.row } column: ${it.column } value: ${it.value }\n" )}
////                print("\n")
////                print("node: " )
////                printPath(node)
////                print("\n")
////            }
////            printPath(dist)
////            if (dist != Int.MAX_VALUE) println("Shortest Path is $dist") else println("Shortest Path doesn't exist")
//        }
//


}



