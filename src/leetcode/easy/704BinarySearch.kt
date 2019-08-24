package leetcode.easy

class BinarySearch {
    fun search(nums: IntArray, target: Int): Int {
        var low = 0
        var high = nums.size - 1
        while (low <= high) {
            val mid = (low + high) ushr 1
            when {
                target < nums[mid] -> high = mid - 1
                target > nums[mid] -> low = mid + 1
                else -> return mid
            }
        }
        return -1
    }
}