package leetcode.datastructure.array;

// 数学
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
    class dp{
        public int maxChunksToSorted(int[] arr) {
            int n = arr.length;
            int[] maxOfLeft = new int[n];
            int[] minOfRight = new int[n];
    
            maxOfLeft[0] = arr[0];
            for (int i = 1; i < n; i++) {
                maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
            }
    
            minOfRight[n - 1] = arr[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
            }
    
            int res = 0;
            for (int i = 0; i < n - 1; i++) {
                if (maxOfLeft[i] <= minOfRight[i + 1]) res++;
            }
    
            return res + 1;
        }
    }
}
