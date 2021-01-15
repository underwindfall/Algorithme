package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/merge-sorted-array/
public class MergeSortedArray88 {
    class DoubleIndex {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] nums = new int[m + n];
            int count = 0;
            int i = 0, j = 0;
            while (count <= m + n) {
                if (i == m) {
                    while (j < n) {
                        nums[count++] = nums2[j++];
                    }
                    break;
                }
                if (j == n) {
                    while (i < m) {
                        nums[count++] = nums1[i++];
                    }
                    break;
                }

                if (nums1[i] < nums2[j]) {
                    nums[count++] = nums1[i++];
                } else {
                    nums[count++] = nums2[j++];
                }
            }

            for (int k = 0; k < m + n; k++) {
                nums1[k] = nums[k];
            }
        }

    }

    // 这里的方法是从尾部开始开始比较
    // 任意时刻 nums1 p1 存放的数字剩余数字m-p1-1
    // 任意时刻 nums2 p2 存放的数字剩余数字n-p2-1
    // m+n- p1-1 >=m-p1-1+n-p2-1 =>p2>=−1
    class ReverseDoubleIndex {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1, p2 = n - 1;
            int tail = m + n - 1;
            int cur;
            while (p1 >= 0 || p2 >= 0) {
                if (p1 == -1) {
                    cur = nums2[p2--];
                } else if (p2 == -1) {
                    cur = nums1[p1--];
                } else if (nums1[p1] > nums2[p2]) {
                    cur = nums1[p1--];
                } else {
                    cur = nums2[p2--];
                }
                nums1[tail--] = cur;
            }
        }
    }

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = 0, p2 = 0;
            int[] sorted = new int[m + n];
            int cur;
            while (p1 < m || p2 < n) {
                if (p1 == m) {
                    cur = nums2[p2++];
                } else if (p2 == n) {
                    cur = nums1[p1++];
                } else if (nums1[p1] < nums2[p2]) {
                    cur = nums1[p1++];
                } else {
                    cur = nums2[p2++];
                }
                sorted[p1 + p2 - 1] = cur;
            }
            for (int i = 0; i != m + n; ++i) {
                nums1[i] = sorted[i];
            }
        }
    }

}
