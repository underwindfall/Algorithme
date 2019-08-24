package swordoffer.exercise3

/**
 * 第3题
 * 一个二维数组，每一行从左到右递增，每一列从上到下递增．
 * 输入一个二维数组和一个整数，判断数组中是否含有整数
 *
 */
fun main(args: Array<String>) {
    val inputArray = arrayOf(2, 3, 1, 0, 2, 5, 3)
//    findDuplicationArrayByHashMap(inputArray).forEach {
//        print(it)
//    }
    print(findDuplicationNoChangOrderWithOutHelpArray(inputArray))

}

//1.利用HashMap的查找快捷性来读取数组，但是牺牲了空间
// espace: O(n)
fun findDuplicationArrayByHashMap(inputArray: Array<Int>): MutableList<Int> {
    val hashMap = hashMapOf<Int, Int>()
    val outputList = mutableListOf<Int>()
    inputArray.forEachIndexed { index, value ->
        if (!hashMap.containsValue(value)) hashMap.put(index, value)
        else outputList.add(value)
    }
    return outputList
}

// 改变了数组
//注意到数字都在0-n-1的范围，如果没有重复的数字，排序后，数字i应该正好在下标为i的位置。所以我们从头扫描数组，扫到下标为i的位置时，判断值是否为i，是则扫描下一位；
// 如果不是，比如它为m，我们判断它是否和下标为m的数字相同，相同则说明出现重复数字，不同我们就交换这两个数字，这样m就到了下标为m的位置。重复这个过程就能找到重复的数字
fun findDuplicationArray(inputArray: Array<Int>): Int {
    for (index in inputArray.indices) {
        while (inputArray[index] != index) {
            if (inputArray[index] == inputArray[inputArray[index]]) {
                return inputArray[index]
            }
            inputArray[index] = inputArray[inputArray[index]].also { inputArray[inputArray[index]] = inputArray[index] }
        }
    }
    return -1
}

//2.不修改数组找出重复的数字
//在一个长度为n+1的数组里的所有数字都在1~n的范围内，所以数组中至少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改出入的数组。
//例如，{2，3，5，4，3，2，6，7} ===>2 or 3
//使用辅助数组，增加空间占用但节约时间）
fun findDuplicationNoChangOrder(inputArray: Array<Int>): Int {
    val helpArray = arrayOfNulls<Int>(inputArray.size + 1)
    for (index in inputArray.indices) {
        if (inputArray[index] == helpArray[inputArray[index]]) {
            return inputArray[index]
        }
        helpArray[inputArray[index]] = inputArray[index]
    }
    return -1
}

//2.不修改数组找出重复的数字
//在一个长度为n+1的数组里的所有数字都在1~n的范围内，所以数组中至少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改出入的数组。
//例如，{2，3，5，4，3，2，6，7} ===>2 or 3
//利用二分法来进行解答
//时间复杂度O(nlogn)
//空间复杂度O(1)
fun findDuplicationNoChangOrderWithOutHelpArray(inputArray: Array<Int>): Int {

    fun countRange(numbers: Array<Int>, start: Int, end: Int): Int {
        return numbers.filter {
            it in start..end
        }.size
    }


    var start = 1
    var end = inputArray.size - 1
    while (end >= start) {
        val middle: Int = (end - start) / 2 + start
        val count = countRange(inputArray, start, middle)
        if (end == start) {
            if (count > 1) return start else break
        }
        if (count > middle - start + 1)
            end = middle
        else
            start = middle + 1

    }

    return -1
}