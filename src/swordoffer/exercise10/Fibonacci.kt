package swordoffer.exercise10

import kotlin.math.pow

fun main(args: Array<String>) {
//    println(fibonacci1().take(10).toList())
//    print(fibonacci2(100))
//    print(fibonacci3(100))
    print(frogStep3(4))
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

////补充
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//注意：给定 n 是一个正整数。

//可以把爬楼梯当作是一个function，
//第一次爬1次楼梯的话，那剩下跳法的数目就是function（N—1）
//第一次爬2次楼梯的话，那剩下跳法的数目就是function（N—2）
// f(n) = f(n-1)+f(n-2)
fun frogStep1(n: Int): Int {
    return when (n) {
        1, 2 -> n
        else -> frogStep1(n - 1) + frogStep1(n - 2)
    }
}


fun frogStep2(n: Int): Int {
    var firstNumber = 1
    var secondNumber = 2
    var targetNumber = n
    when (n) {
        1, 2 -> return targetNumber
        else -> for (i in 3..n) {
            targetNumber = firstNumber + secondNumber
            firstNumber = secondNumber
            secondNumber = targetNumber
        }
    }
    return targetNumber
}


//拓展
//可以跳1节台阶
//可以跳2节台阶
//可以跳n节台阶
// f(n)= 2^(n-1)

fun frogStep3(n: Int): Int {
    return 2.0f.pow(n.toFloat() - 1).toInt()
}