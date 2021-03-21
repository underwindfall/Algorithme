package leetcode.datastructure.array;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
// https://leetcode-cn.com/problems/degree-of-an-array/description/
// espace 
public class FindShortestSubArray697 {
    // time O(N)
    // espace O(N)
    class MapSolution {
        public int findShortestSubArray(int[] nums) {
            Map<Integer, Integer> left = new HashMap<>();
            Map<Integer, Integer> right = new HashMap<>();
            Map<Integer, Integer> count = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int x = nums[i];
                if (left.get(x) == null) {
                    left.put(x, i);
                }
                right.put(x, i);
                count.put(x, count.getOrDefault(x, 0) + 1);
            }
            int ans = nums.length;
            int degree = Collections.max(count.values());
            for (int x : count.keySet()) {
                if (count.get(x) == degree) {
                    ans = Math.min(ans, right.get(x) - left.get(x) + 1);
                }
            }
            return ans;
        }
    }

    class ArraySolution {
        public int findShortestSubArray(int[] nums) {
            int[] count = new int[Integer.MAX_VALUE];
            int[] b = new int[Integer.MAX_VALUE];
            int max = 0;

            for (int i = 0; i < nums.length; i++) {
                int t = nums[i];
                b[t] = i;// get the last value index
                count[t]++;
                max = Math.max(max, count[t]);
            }
            int ans = nums.length;
            for (int i = 0; i < nums.length; i++) {
                if (count[nums[i]] == max) {
                    ans = Math.min(ans, b[nums[i]] - i + 1);
                    count[nums[i]] = 0;
                }
            }
            return ans;
        }
    }

    public int findShortestSubArray(int[] nums) {
        // 找出数组中最大和最小的数字
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int degree = 0;
        // 统计元素出现次数的数组. 把原有数组的数全部减min, 就可以节省min个空间,
        // 比如原数组的数字 2 是最小的min, 在计算原数组数字 4 出现数次的时候, 就用count数组的 4 - min = 2 的下标存放
        // 也就是说, 从count取出下标为2的值, 就代表了 2 + min = 4 这个数出现的次数
        int[] count = new int[max - min + 1];
        for (int num : nums) {
            // 拿到num之后, 找到count数组对应的下标, ++, 这个就可以计算出出现的次数, 顺便把最大的次数存到degree
            degree = Math.max(degree, ++count[num - min]);
        }
        if (degree == 1) {
            return 1;
        }
        // 初始化最小子数组长度为数组长度
        int subarrayLen = nums.length;
        for (int i = 0; i < count.length; i++) {

            // 找到度对应的数字
            if (count[i] != degree) {
                continue;
            }
            // 还原原始的真正数字
            int num = i + min;
            // 此数字出现的最左和最右的下标
            int leftIndex = 0, rightIndex = nums.length - 1;
            // 左右未重合前, 找到这个数字出现的最左边的下标, 左下标往右移动
            while (leftIndex < rightIndex && num != nums[leftIndex]) {
                leftIndex++;
            }
            // 左右未重合前, 找到这个数字出现的最右边的下标, 右下标往左移动
            while (leftIndex < rightIndex && num != nums[rightIndex]) {
                rightIndex--;
            }
            subarrayLen = Math.min(subarrayLen, rightIndex - leftIndex + 1);
        }
        return subarrayLen;
    }

    public static void main(String[] args) {
        FindShortestSubArray697 findDuplicateNumber287 = new FindShortestSubArray697();
        int[] nums = new int[] { 1, 2, 2, 3, 1 };
        System.out.println(findDuplicateNumber287.new MapSolution().findShortestSubArray(nums));
    }
}
