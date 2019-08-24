package leetcode.easy

/**
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

说明：不要使用任何内置的库函数，如  sqrt。

示例 1：

输入：16
输出：True

示例 2：

输入：14
输出：False
 */


//解题思路
/**
 * 1. 完全平方数就是开平方根后还是一个整数
 * 2.升平方根有个结论是一个非负数n的平方根小于n/2+1，也就是利用二分查找在（0，n/2+1）范围内查找
 * 3.如果找到了就说明是一个完全平方数否则不是
 */
class IsPerfectSquare {
    fun isPerfectSquare(num: Int): Boolean {
        var low = 0L
        var high = (num / 2 + 1).toLong()

        while (low <= high) {
            val mid = (high + low) ushr 1
            val sqrtResult = mid * mid
            when {
                num > sqrtResult -> low = mid + 1
                num < sqrtResult -> high = mid - 1
                else -> return true
            }
        }

        return false
    }
}