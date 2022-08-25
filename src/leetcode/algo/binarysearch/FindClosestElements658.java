package leetcode.algo.binarysearch;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/find-k-closest-elements/
public class FindClosestElements658 {
    // time O(logN + k)
    // space O(1)
    class Binarysearch_DoubleIndex {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int right = binarySearch(arr, x);
            int left = right - 1;
            while (k-- > 0) {
                if (left < 0) {
                    right++;
                } else if (right >= arr.length) {
                    left--;
                } else if (x - arr[left] <= arr[right] - x) {
                    left--;
                } else {
                    right++;
                }
            }
            List<Integer> ans = new ArrayList<Integer>();
            for (int i = left + 1; i < right; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }

        int binarySearch(int[] arr, int x) {
            int low = 0, high = arr.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] >= x) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
    }

    // time O(logN + k)
    // space O(1)
    class DoubleIndex {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> ans = new ArrayList<>();
            int size = arr.length;
            int left = 0;
            int right = size - 1;
            int removeNums = size - k;
            while (removeNums > 0) {
                if (x - arr[left] <= arr[right] - x) {
                    right--;
                } else {
                    left++;
                }
                removeNums--;
            }
            for (int i = left; i < left + k; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
    }

    // time O(logN + k)
    // space O(1)
    class Binarysearch {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> ans = new ArrayList<>();
            int size = arr.length;

            int left = 0;
            int right = size - k;
            while (left < right) {
                int mid = (left + right) >>> 1;
                // 尝试从长度为 k + 1 的连续子区间删除一个元素
                // 从而定位左区间端点的边界值
                if (x - arr[mid] > arr[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            for (int i = left; i < left + k; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
    }
}
