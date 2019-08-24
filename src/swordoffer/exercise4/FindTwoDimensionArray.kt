package swordoffer.exercise4


fun main(args: Array<String>) {
    val inputArray: Array<IntArray> = arrayOf(
        intArrayOf(1, 2, 8, 9),
        intArrayOf(2, 4, 9, 12),
        intArrayOf(4, 7, 10, 13),
        intArrayOf(6, 8, 11, 15)
    )
    assert(findTwoDimensionArray(inputArray, 10)) {
        "Not found it"
    }
}


//面试题4：二维数组中的查找
// 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按
// 照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个
// 整数，判断数组中是否含有该整数。

fun findTwoDimensionArray(inputArray: Array<IntArray>, target: Int): Boolean {
    if (inputArray.isEmpty()) {
        return false
    }
    var row = 0
    var column = inputArray[0].size - 1

    while (row < inputArray.size && column >= 0) {
        when {
            inputArray[row][column] == target -> return true
            inputArray[row][column] > target -> column--
            inputArray[row][column] < target -> row++
        }
    }
    return false

}