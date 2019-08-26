package leetcode.easy


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
