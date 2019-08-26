package swordoffer.exercise10

fun main(args: Array<String>) {
//    println(fibonacci1().take(10).toList())
//    print(fibonacci2(100))
//    print(fibonacci3(100))
}


// 面试题10：斐波那契数列
// 题目：写一个函数，输入n，求斐波那契（Fibonacci）数列的第n项


//kotlin特性
fun fibonacci1(): Sequence<Int> {
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, ...
    return generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) }).map { it.first }
}

// 递归算法,速度较慢
fun fibonacci2(n: Int): Int {
    return when (n) {
        1, 2 -> n
        else -> fibonacci2(n - 1) + fibonacci2(n - 2)
    }
}

//减少重复性的运算
fun fibonacci3(n: Int): Long {
    var firstNumber = 1L
    var secondNumber = 2L
    var targetNumber = firstNumber
    when (n) {
        1 -> return firstNumber
        2 -> return secondNumber
        else -> for (i in 3..n) {
            targetNumber = firstNumber + secondNumber
            firstNumber = secondNumber
            secondNumber = targetNumber
        }
    }
    return targetNumber
}


////补充
///**
// * 用以下公式将斐波纳契数列问题转换成矩阵的乘方，并用递归求解矩阵的乘方
// * [[f(n),f(n-1)],[f(n-1),f(n-2)]] = [[1,1],[1,0]]^(n-1)
// * @param n
// * @return 斐波纳契数列的第n项
// */
//fun fibonacci4(n: Int): Long {
//    if (n == 1 || n == 2) return n.toLong()
//    val matrix: Array<IntArray> = arrayOf(intArrayOf())
//
//}