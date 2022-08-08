package leetcode.algo.binarysearch;

// https://leetcode.cn/problems/kth-missing-positive-number/
public class FindKthPositive1514 {
    // time O(n)
    // space O(1)
    class Iterative {
        public int findKthPositive(int[] arr, int k) {
            int missCount = 0, lastMissing = -1;
            int curr = 1, index = 0;
            for (missCount = 0; missCount < k; curr++) {
                if (arr[index] == curr) {
                    index = (index + 1 < arr.length) ? index + 1 : index;
                } else {
                    lastMissing = curr;
                    missCount++;
                }
            }
            return lastMissing;
        }
    }

    // time O(logN)
    // space O(1)
    class BinarySearch {
        public int findKthPositive(int[] arr, int k) {
            int l = 0, r = arr.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (arr[mid] - mid - 1 < k) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return l + k;
        }
    }
}
