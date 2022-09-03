package leetcode.datastructure.hash;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/first-missing-positive/
//cf 442
// cf 448
public class FirstMissingPositive41 {
    // time O(n)
    // https://leetcode.cn/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    // time O(n)
    // space O(n)
    class Hash {
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;

            Set<Integer> hashSet = new HashSet<>();
            for (int num : nums) {
                hashSet.add(num);
            }

            for (int i = 1; i <= len; i++) {
                if (!hashSet.contains(i)) {
                    return i;
                }
            }
            return len + 1;
        }
    }
}
