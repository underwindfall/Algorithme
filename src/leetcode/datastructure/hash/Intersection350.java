package leetcode.datastructure.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection350 {
    class DoubleIndex {
        public int[] intersection(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int[] intersection = new int[Math.min(nums1.length, nums2.length)];
            int index = 0;
            int i = 0, j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    intersection[index] = nums1[i];
                    i++;
                    j++;
                    index++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }

            return Arrays.copyOfRange(intersection, 0, index);
        }
    }

    class BinarySearch {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            Arrays.sort(nums2);
            for (int number : nums1) {
                if (binarySearch(nums2, number) && !set.contains(number)) {
                    set.add(number);
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
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    return true;
                }
            }
            return false;
        }
    }
}
