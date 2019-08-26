package leetcode.easy

//思路
//和斐波那契数列类似
//可以把爬楼梯当作是一个function，
//第一次爬1次楼梯的话，那剩下跳法的数目就是function（N—1）
//第一次爬2次楼梯的话，那剩下跳法的数目就是function（N—2）
// f(n) = f(n-1)+f(n-2)
class ClimbStairs {
    fun climbStairs(n: Int): Int {
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
}
