package leetcode.contest.weekly;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/contest/weekly-contest-235/
public class Weekly235 {
    // https://leetcode-cn.com/problems/truncate-sentence/
    class Leetcode_5722 {
        public String truncateSentence(String s, int k) {
            int cnt = 0;
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                if (s.toCharArray()[i] == ' ')
                    cnt++;
                if (cnt == k)
                    break;
                res += s.toCharArray()[i];
            }
            return res;

        }
    }

    // https://leetcode-cn.com/problems/finding-the-users-active-minutes/
    class Leetcode_5723 {

        public int[] findingUsersActiveMinutes(int[][] logs, int k) {
            Map<Integer, Set<Integer>> map = new HashMap<>();

            for (int[] log : logs) {
                if (!map.containsKey(log[0])) {
                    Set<Integer> times = new HashSet<>();
                    map.put(log[0], times);
                }
                map.get(log[0]).add(log[1]);
            }
            int[] res = new int[k];
            for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
                res[entry.getValue().size() - 1]++;
            }

            return res;

        }
    }

    // https://leetcode-cn.com/problems/minimum-absolute-sum-difference/
    class Leetcode_5724 {
        public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
            // 复制一个nums1
            int[] n = new int[nums1.length];
            for (int i = 0; i < n.length; i++) {
                n[i] = nums1[i];
            }
            // 对n排序是使用二分的前提
            Arrays.sort(n);

            // 用max储存Max(abs(ai-ci)-abs(ai-bi))(i=0,1,2,…，n-1)
            int max = 0;

            // 求max
            for (int i = 0; i < nums1.length; i++) {
                // fun返回最优的abs(a-b)
                max = Math.max(max, Math.abs(nums1[i] - nums2[i]) - bSearch(n, nums2[i]));
            }
            // 长整型防溢出
            long ans = 0;
            for (int i = 0; i < nums1.length; i++) {
                ans += Math.abs(nums1[i] - nums2[i]);
            }
            // 减去max就好了
            ans -= max;
            ans = ans % 1000000007;

            return (int) ans;
        }

        int bSearch(int[] array, int target) {
            int l = 0, r = array.length - 1, mid;
            int la = array[l], ra = array[r];
            while (l <= r) {
                mid = (r + l) / 2;
                if (array[mid] == target) {
                    return 0;
                } else if (array[mid] < target) {
                    l = mid + 1;
                    la = array[mid];
                } else {
                    r = mid - 1;
                    ra = array[mid];
                }
            }

            return Math.min(Math.abs(target - la), Math.abs(target - ra));
        }

    }

    // https://leetcode-cn.com/problems/number-of-different-subsequences-gcds/
    class Leetcode_5725 {
        public int countDifferentSubsequenceGCDs(int[] nums) {
            boolean[] visited = new boolean[200005];
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                visited[num] = true;
                max = Math.max(max, num);
            }
            int count = 0;
            // 公约数可能的范围【1, max】
            for (int i = 1; i <= max; i++) {
                int commonGCD = -1;
                // 检查所有i的倍数
                for (int j = i; j <= max; j += i) {
                    if (visited[j]) {
                        if (commonGCD == -1) {
                            commonGCD = j;
                        } else {
                            commonGCD = gcd(commonGCD, j);
                        }
                    }
                }
                if (i == commonGCD) {
                    count++;
                }
            }
            return count;
        }

        private int gcd(int x, int y) {
            if (x == 0) {
                return y;
            }
            return gcd(y % x, x);
        }
    }
}
