package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/max-chunks-to-make-sorted/description/
public class MaxChunksToSorted769 {
    // time O(1)
    // espace O(1)
    class ExhasautionSolution {
        public int maxChunksToSorted(int[] arr) {
            int ans = 0, maxRight = 0;
            for (int i = 0; i < arr.length; i++) {
                maxRight = Math.max(arr[i], maxRight);
                if (maxRight == i) {
                    ans++;
                }
            }
            return ans;
        }
    }

    // O(N)
    // O(1)
    class DoubleIndexSolution {
        public int maxChunksToSorted(int[] arr) {
            int count = 0;
            for (int left = 0; left < arr.length;) {
                int right = left + 1;
                for (; right <= arr[left]; right++) {
                    if (arr[left] < arr[right]) {
                        // 说明在left内部就找到了比他本身大的数不能成立一个block
                        break;
                    }
                }

                if (right <= arr[left]) {
                    left = right;
                } else {
                    count++;
                    left = arr[left] + 1;
                }
            }

            return count;
        }
    }
}
