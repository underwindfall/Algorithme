package leetcode.datastructure.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 时间换空间
// https://leetcode-cn.com/problems/set-mismatch/description/
public class SetMismatch645 {

    // time O(N)
    // espace O(N)
    class TransformNumberToCounter {
        public int[] findErrorNums(int[] nums) {
            int[] arr = new int[nums.length + 1];
            int dup = -1;
            int missing = 1;
            for (int i = 0; i < nums.length; i++) {
                arr[nums[i]] += 1;
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    missing = i;
                } else if (arr[i] == 2) {
                    dup = i;
                }
            }
            return new int[] { dup, missing };
        }
    }

    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)，使用恒定的额外空间。
    class FastSlowIndex {
        public int[] findErrorNums(int[] nums) {
            int dup = -1, missing = -1;
            for (int i = 1; i <= nums.length; i++) {
                int count = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] == i)
                        count++;
                }
                if (count == 2)
                    dup = i;
                else if (count == 0)
                    missing = i;
                if (dup > 0 && missing > 0)
                    break;
            }
            return new int[] { dup, missing };
        }
    }

    // time O(n)
    // espace O(N)
    class MapSolution {
        public int[] findErrorNums(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            int dup = -1, missing = 1;
            for (int n : nums) {
                map.put(n, map.getOrDefault(n, 0) + 1);
            }
            for (int i = 1; i <= nums.length; i++) {
                if (map.containsKey(i)) {
                    if (map.get(i) == 2)
                        dup = i;
                } else
                    missing = i;
            }
            return new int[] { dup, missing };
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 2 };
        SetMismatch645 mismatch645 = new SetMismatch645();
        FastSlowIndex slowIndex = mismatch645.new FastSlowIndex();
        System.out.println(Arrays.toString(slowIndex.findErrorNums(arr)));
    }
}
