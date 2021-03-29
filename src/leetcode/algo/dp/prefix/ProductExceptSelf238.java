package leetcode.algo.dp.prefix;

// https://leetcode-cn.com/problems/product-of-array-except-self
public class ProductExceptSelf238 {
    // espace O(N)
    // time O(N)
    // 题目可以转换为 左侧的乘积*右侧的乘积
    // 左侧乘积可以通过 L[i] = L[i - 1] * nums[i - 1] i = 0 L[0] = 1
    // 右侧乘积可以通过 R[i] = R[i + 1] * nums[i + 1] i = length - 1 R[length - 1] = 1
    class PrefixSuffix {
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;
            // L 和 R分别在左右两侧的乘积列表
            int[] L = new int[length];
            int[] R = new int[length];

            int[] answer = new int[length];
            // L[i] 为索引 i 左侧所有元素的乘积
            // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
            L[0] = 1;
            for (int i = 1; i < length; i++) {
                L[i] = L[i - 1] * nums[i - 1];
            }

            // R[i] 为索引 i 右侧所有元素的乘积
            // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
            R[length - 1] = 1;
            for (int i = length - 2; i >= 0; i--) {
                R[i] = R[i + 1] * nums[i + 1];
            }

            // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
            for (int i = 0; i < length; i++) {
                answer[i] = L[i] * R[i];
            }

            return answer;
        }
    }
}
