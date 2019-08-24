package leetcode.easy

import java.util.*

fun main(args: Array<String>) {
    Intersection().intersection(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2)).forEach {
        print(it)
    }
}

class Intersection {

    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val len1 = nums1.size
        val len2 = nums2.size
        val less = if (len1 < len2) nums1 else nums2
        val more = if (len1 < len2) nums2 else nums1

        val lessSet: HashSet<Int> = hashSetOf<Int>()

        val moreSet: HashSet<Int> = hashSetOf<Int>()

        less.forEach { lessSet.add(it) }

        more.filter {
            !moreSet.contains(it) && lessSet.contains(it)
        }.forEach {
            moreSet.add(it)
        }

        return moreSet.toIntArray()
    }
}