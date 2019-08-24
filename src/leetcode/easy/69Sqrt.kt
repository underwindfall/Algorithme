package leetcode.easy

class Sqrt {
    fun mySqrt(x: Int): Int {
        var low = 0L
        var high = (x / 2 + 1).toLong()
        while (low <= high) {
            val mid = (high + low) ushr 1
            val sqrtResult = mid * mid
            when {
                x < sqrtResult -> high = mid - 1
                x > sqrtResult -> low = mid + 1
                else -> return mid.toInt()
            }
        }
        return high.toInt()
    }
}