package leetcode.easy

class GuessNumber {
    fun guessNumber(n: Int): Int {
        var low = 1
        var high = n
        while (low < high) {
            val mid = (n + 1) ushr 1
            val guessNum = guess(mid)
            when (guessNum) {
                -1 -> high = mid - 1
                1 -> low = mid + 1
                else -> return mid
            }
        }
        return -1
    }

    fun guess(num: Int): Int {
        return -1
    }
}