package leetcode.easy

/**
 * 解题思路
 * 1.首先明白峰定的概念，以峰顶为界限一边是顺序递增一边是递减，所以可以二分查找，值得注意的是
 * 峰顶的位置一定是最后找到的，并且low和high相遇的位置，峰顶位置一定存在
 * 2.如果每次中间位置的那个数比下一个未知的数小，那说明峰顶位置在右后半部分且这个数本身不是峰顶，所以是mid+1
 * 3.反之小的话就是调小最高上限
 * 4.最后就能相遇了
 */
class PeakIndexInMountainArray {
    fun peakIndexInMountainArray(A: IntArray): Int {
        var lo = 0
        var hi = A.size - 1
        while (lo < hi) {
            val mi = lo + (hi - lo) / 2
            if (A[mi] < A[mi + 1])
                lo = mi + 1
            else
                hi = mi
        }
        return lo
    }
}