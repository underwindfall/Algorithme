package leetcode.easy

class FirstBadVersion {
    fun firstBadVersion(n: Int): Int {
        var low = 1
        var high = n
        while (low < high) {
            val mid = (low + high) ushr 1
            if (isBadVersion(mid)) {
                high = mid
            } else {
                low = mid + 1
            }
        }
        return low
    }

    private fun isBadVersion(value: Int): Boolean = false
}