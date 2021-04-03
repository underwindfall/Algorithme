package leetcode.contest.biweekly;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/contest/biweekly-contest-49
public class week49 {

    // https://leetcode-cn.com/problems/determine-color-of-a-chessboard-square/
    class LC_5705 {
        // 奇偶讨论
        public boolean squareIsWhite(String coordinates) {
            return (coordinates.charAt(0) + coordinates.charAt(1)) % 2 != 0;
        }
    }

    // https://leetcode-cn.com/problems/sentence-similarity-iii/
    class LC_5706 {
        public boolean areSentencecesSimiliar(String sentence1, String sentence2) {
            String[] arr1 = sentence1.split(" ");
            String[] arr2 = sentence2.split(" ");

            int length1 = arr1.length;
            int length2 = arr2.length;

            int left = 0, right = 0;
            // 从左边遍历，遇到不同的词停止
            while (left < length1 && left < length2) {
                if (arr1[left].equals(arr2[left])) {
                    left++;
                } else {
                    break;
                }
            }

            if (left == length1 && left == length2)
                return true;
            // 从右边遍历，遇到不同的词停止
            while (right < length1 && right < length2) {
                if (arr1[length1 - right - 1].equals(arr2[length2 - right - 1])) {
                    right++;
                } else {
                    break;
                }
            }

            return (right + left) == Math.min(length1, length2);
        }
    }

    // https://leetcode-cn.com/problems/count-nice-pairs-in-an-array
    class LC_5708 {
        public int countNicePairs(int[] nums) {
            // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
            // nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
            int mod = (int) 1e9 + 7;
            Map<Integer, Integer> count = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int key = nums[i] - rev(nums[i]);
                count.put(key, count.getOrDefault(key, 0) + 1);
            }
            long result = 0;
            for (int key : count.keySet()) {
                long times = count.get(key);
                result = (result + (times) * (times - 1) / 2) % mod;
            }
            return (int) (result % mod);
        }

        int rev(int num) {
            int result = 0;
            while (num > 0) {
                int dig = num % 10;
                result = result * 10 + dig;
                num /= 10;
            }
            return result;
        }
    }

    // https://leetcode-cn.com/problems/maximum-number-of-groups-getting-fresh-donuts/
    class LC_5707 {
        // 贪心算法+dfs+map
        public int maxHappyGroups(int batchSize, int[] groups) {
            int res = 0;
            // 记录当前满足customer的情况数据
            int[] cnts = new int[batchSize];

            for (int group : groups) {
                // 贪心算法因为去模后 余数相同的值是一致的
                int g = group % batchSize;
                if (g == 0) {
                    res++;
                } else if (cnts[batchSize - g] > 0) {
                    res++;
                    // 客户可以满意的离开了
                    cnts[batchSize - g]--;
                } else {
                    cnts[g]++;
                }
            }

            Map<String, Integer> map = new HashMap<>();
            return res + dfs(cnts, /* remain 当前剩下的甜甜圈 **/ 0, batchSize, map);
        }

        int dfs(int[] cnts, int remain, int batchSize, Map<String, Integer> cache) {
            String key = getKey(cnts);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            int res = 0;

            for (int i = 1; i < cnts.length; i++) {
                // 当前group都被满足了 直接过
                if (cnts[i] == 0)
                    continue;
                // remain != 0 <-> 还剩下有group被处理（可以吃到甜甜圈） 否则没有
                int tmp = (remain == 0 ? 1 : 0);
                // 先处理remain 那组 假设 处理cnts[i]
                cnts[i]--;
                tmp += dfs(cnts, (remain - i + batchSize)%batchSize, batchSize, cache);
                // 状态恢复
                cnts[i]++;
                res = Math.max(res, tmp);
            }
            cache.put(key, res);
            return res;
        }

        String getKey(int[] cnts) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cnts.length; i++) {
                sb.append(i + "#" + cnts[i]);
            }
            return sb.toString();
        }
    }
}
