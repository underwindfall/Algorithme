package swordoffer.exercise5

// 面试题5：替换空格
// 题目：请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，
// 则输出“We%20are%20happy.”。
fun main(args: Array<String>) {
    val input = "We are happy."
    print(replaceBlank1(input))
    print(replaceBlank2(input))

    val A1 = intArrayOf(1, 3, 5, 7, 9, 0, 0, 0, 0, 0)
    val A2 = intArrayOf(0, 2, 4, 6, 8)
    mergeArray(A1, A2).forEach {
        print(it)
    }
}

/**
 * 替换空格（将字符串中的空格替换为其他字符串)
 */
fun replaceBlank1(input: String): String {
    val builder = with(StringBuilder()) {
        input.forEach {
            if (it.toString() == " ") {
                this.append("%20")
            } else {
                this.append(it)
            }
        }
        this
    }
    return builder.toString()
}

/**
 * 解法2：从后往前复制
 *
 * @param input
 * @return
 */
fun replaceBlank2(input: String): String {
    val blankNum = input.filter { it.toString() == " " }.length
    val length = input.length
    val newLength = length + blankNum * 2
    val newCharArray = CharArray(newLength)
    var newIndex = newCharArray.size - 1
    for (i in (length - 1) downTo 0) {
        if (input[i].toString() == " ") {
            newCharArray[newIndex--] = '0'
            newCharArray[newIndex--] = '2'
            newCharArray[newIndex--] = '%'
        } else {
            newCharArray[newIndex--] = input[i]
        }
    }
    return String(newCharArray)
}


/**
 * 相关题目：
 * 有两个排序的数组A1和A2，内存在A1的末尾有足够多的空余空间容纳A2
 * 请实现一个函数把A2中所有的数字插入A1中，并且所以所有的数字都是排序的
 */
//思路：反向思维将数组进行末尾比较，从后向前比较，若
//A2的数组的里数字大于A1里面的数字就向后插入，如果是小于就向前插入，如果等于就移动下一个
fun mergeArray(firstArray: IntArray, secondArray: IntArray): IntArray {
    fun insertArray(insertNumber: Int, index: Int, array: IntArray) {
        var temp = insertNumber
        var cache: Int
        for (i in index until array.size) {
            cache = array[i]
            array[i] = temp
            temp = cache
        }
    }


    var index1 = firstArray.size - 1
    firstArray.filter { it == 0 }.forEach { _ -> index1-- }
    var index2 = secondArray.size - 1
    while (index2 >= 0) {
        if (secondArray[index2] < firstArray[index1]) {
            if (index1 == 0 || firstArray[index1 - 1] < secondArray[index2]) {
                insertArray(secondArray[index2], index1, firstArray)
                index1--
                index2--
            }
        } else {
            index1++
        }
    }
    return firstArray

}


