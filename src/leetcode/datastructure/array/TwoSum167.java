package leetcode.datastructure.array;
// https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
public class TwoSum167 {
    // time O(N*logN)
    // espace O(1)
    class Binary {
        public int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; i++) {
                int start = i + 1;
                int end = numbers.length - 1;
                while (start <= end) {
                    int mid = (end - start) / 2 + start;
                    if (numbers[i] + numbers[mid] > target) {
                        end = mid - 1;
                    } else if (numbers[i] + numbers[mid] < target) {
                        start = mid + 1;
                    } else {
                        return new int[] { i + 1, mid + 1 };
                    }
                }
            }

            return new int[] { -1, -1 };
        }
    }

    // time O(N)
    // espace O(1)
    class DoubleIndex {
        public int[] twoSum(int[] numbers, int target) {
            int start = 0, end = numbers.length - 1;
            while (start < end) {
                if (numbers[start] + numbers[end] > target) {
                    end--;
                } else if (numbers[start] + numbers[end] < target) {
                    start++;
                } else {
                    return new int[] { start + 1, end + 1 };
                }
            }
            return new int[] { -1, -1 };
        }
    }
}
