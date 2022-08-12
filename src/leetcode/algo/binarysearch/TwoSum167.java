package leetcode.algo.binarysearch;
// https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
class TwoSum167 {
    /**
     * 这边已经是最大的值了
     * 
     * 二分法针对数组的每个点numbers i
     * 
     * 他剩余的选择是[i+1, numbers.length)
     * 
     * 因为有序所以二分法
     */
    class BinarySearch {
        public int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; i++) {
                int left = i + 1, right = numbers.length - 1;
                while (left <= right) {
                    int mid = (right - left) / 2 + left;
                    if (numbers[i] + numbers[mid] == target) {
                        // 这一步单纯为了凑数 其实题目变化的没必要做
                        return new int[] { i + 1, mid + 1 };
                    } else if (numbers[i] + numbers[mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return new int[] { -1, -1 };
        }
    }

    /**
     * 本质其实通过双指针操作
     * 
     */
    class DoubleIndex {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0, right = numbers.length - 1;
            while (left <= right) {
                // numbers[left] + numbers[right] 其实意味着你是你们这行最大能获取到的值了，如果还不行，
                // 那意味着你的起点太低了，需要提升你的起点
                // left++
                // 但是如果过小意味你的起点挺高，动你的目标点就好了
                // right-- 
                if (numbers[left] + numbers[right] == target) {
                    // 这一步单纯为了凑数 其实题目变化的没必要做
                    return new int[] { left + 1, right + 1 };
                } else if (numbers[left] + numbers[right] > target) {
                    right = right - 1;
                } else {
                    left++;
                }
            }
            return new int[] { -1, -1 };
        }
    }

}