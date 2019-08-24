package leetcode.easy

/**
 *解题思路
1.第一步思路换成查找，题目求数组两个数之和等于目标数，实际上可以转换为查找目标数减去其中一个数的差的值是否存在该数组，转换成查找问题
2.第二步从时间复杂的去考虑的话可以通过暴力循环嵌套方法解决，但是复杂度为O(n^2)
3.循环加二分法O(n*logn)的复杂度
 **/
class TowSum {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        for (index in numbers.indices) {
            val index2 = binarySearch(numbers, index + 1, target - numbers[index])
            if (index2 != -1) {
                return intArrayOf(index + 1, index2 + 1)
            }
        }
        return intArrayOf()
    }

    private fun binarySearch(numbers: IntArray, startIndex: Int, target: Int): Int {
        var low = startIndex
        var high = numbers.size - 1
        while (low <= high) {
            val mid = (high + low) ushr 1
            when {
                target > numbers[mid] -> low = mid + 1
                target < numbers[mid] -> high = mid - 1
                else -> return mid
            }
        }

        return -1
    }
}