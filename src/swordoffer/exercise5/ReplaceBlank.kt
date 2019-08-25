package swordoffer.exercise5

// 面试题5：替换空格
// 题目：请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，
// 则输出“We%20are%20happy.”。
fun main(args: Array<String>) {
    val input = "We are happy."
    print(replaceBlank2(input))
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



