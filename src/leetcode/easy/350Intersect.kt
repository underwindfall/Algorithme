package leetcode.easy


fun main(args: Array<String>) {
    Intersect().intersect(intArrayOf(4, 9, 5), intArrayOf(9, 4, 9, 8, 4)).forEach {
        print(it)
    }
}

class Intersect {

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        if (nums1.isEmpty())
            return nums1
        if (nums2.isEmpty())
            return nums2
        nums1.sort()
        nums2.sort()
        var i = 0
        var j = 0
        var k = 0
        while (i < nums1.size && j < nums2.size) {
            when {
                nums1[i] < nums2[j] -> i++
                nums2[j] < nums1[i] -> j++
                nums1[i] == nums2[j] -> {
                    nums1[k++] = nums1[i]
                    i++
                    j++
                }
            }
        }
        return nums1.copyOfRange(0, k)
    }
}