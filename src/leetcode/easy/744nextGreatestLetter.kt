package leetcode.easy

/**
 * 首先这题重复利用二分查找思想，但最终要找的不是目标字母，而是查找比目标字母大的最小字母，
 * 简而言之就是找到比目标字母大的升序的字符数组中第一重现的字母
 */
class NextGreatestLetter {
    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        var low = 0
        var high = letters.size - 1
        if (target < letters.first() || target >= letters.last()) {
            return letters.first()
        }
        while (low <= high) {
            val mid = (low + high) ushr 1
            when {
                target < letters[mid] -> high = mid - 1
                else -> low = mid + 1
            }
        }

        return letters[low]
    }
}