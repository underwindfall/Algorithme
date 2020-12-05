package swordoffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
public class LCOF03 {
    // 解题思路
    // 死循环处理
    // 首先排序然后死循环处理
    // time O(N*lgN)
    // espace O(1)
    class LoopSolution {
        public int findRepeatNumber(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] == nums[i]) {
                    return nums[i];
                }
            }
            return -1;
        }
    }

    // time O(N)
    // espace O(N)
    class HashMapSolution {
        public int findRepeatNumber(int[] nums) {
            Map<Integer, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!hashMap.containsKey(nums[i])) {
                    hashMap.put(nums[i], nums[i]);
                } else {
                    return nums[i];
                }
            }
            return -1;
        }
    }
    // 思路 因为是从0到N-1 所以可以看作是每个数字都是在当前位置对应当前数组的值 i = nums[i],否则就行一次呼唤
    // time O(N)
    // espace O(1)
    class SwapSolution {
        public int findRepeatNumber(int[] nums) {
            if (nums == null || nums.length <= 0) {
                return -1;
            }
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != i) {
                    if (nums[i] == nums[nums[i]]) {
                        return nums[i];
                    }
                    swap(nums, i, nums[i]);
                }
            }
            return -1;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
