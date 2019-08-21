package swordoffer.exercise3

/**
 * 第3题
 * 一个二维数组，每一行从左到右递增，每一列从上到下递增．
 * 输入一个二维数组和一个整数，判断数组中是否含有整数
 *
 */
fun main(args: Array<String>) {
    val inputArray = arrayOf(2, 3, 1, 0, 2, 5, 3)
    findDuplicationArray(inputArray).forEach {
        print(it)
    }
}

//espace: O(n)
fun findDuplicationArray(inputArray: Array<Int>): MutableList<Int> {
    val hashMap = hashMapOf<Int, Int>()
    val outputList = mutableListOf<Int>()
    inputArray.forEachIndexed { index, value ->
        if (!hashMap.containsValue(value)) hashMap.put(index, value)
        else outputList.add(value)
    }
    return outputList
}