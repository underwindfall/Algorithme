package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/two-sum/
public class TwoSum1 {
    class Hash {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[] { map.get(target - nums[i]), i };
                }
                map.put(nums[i], i);
            }
            return new int[0];
        }
    }

    class DoubleIndex {
        public int[] twoSum(int[] nums, int target) {
            int index = 0;
            while (index < nums.length) {
                for (int i = index + 1; i < nums.length; i++) {
                    if (nums[i] + nums[index] == target) {
                        return new int[] { index, i };
                    }
                }
                index++;
            }
            return new int[] { -1, -1 };
        }
    }
}
