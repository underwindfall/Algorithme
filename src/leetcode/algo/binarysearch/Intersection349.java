package leetcode.algo.binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/intersection-of-two-arrays/
public class Intersection349 {
    /**
     * 思路一 双指针循环 一个指针遍历第一个，另一个遍历第二个
     * 
     * same -> 可以放入set中
     * 
     * 若nums1 < nums2 i++ 否则j++
     * 
     */
    class DoubleIndex {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0, j = 0;
            Set<Integer> set = new HashSet<>();
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            int[] result = new int[set.size()];
            int index = 0;
            for (int k : set) {
                result[index++] = k;
            }
            return result;
        }
    }

    /**
     * 思路二 二分法
     * 
     * 排序其中的一个方法
     * 
     * 然后通过二分遍历这个另一组数 看存不存在 不存在就放进去
     */
    class BinarySearch {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Set<Integer> set = new HashSet<>();
            for (int num : nums2) {
                if (binarySearch(nums1, num) && !set.contains(num)) {
                    set.add(num);
                }
            }
            int[] result = new int[set.size()];
            int index = 0;
            for (int k : set) {
                result[index++] = k;
            }
            return result;
        }

        boolean binarySearch(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return false;
        }
    }
}
