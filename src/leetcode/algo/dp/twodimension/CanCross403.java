package leetcode.algo.dp.twodimension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/problems/frog-jump/
public class CanCross403 {
    // 这个dp设计的很巧妙
    // 这个map 是 key 是当前石头index，value 是所有的jumpsize的集合
    // 如果当前存在一个value 表明当前的到达的位置则返回则更新这个值
    class DP {
        public class Solution {
            public boolean canCross(int[] stones) {
                HashMap<Integer, Set<Integer>> map = new HashMap<>();
                for (int i = 0; i < stones.length; i++) {
                    map.put(stones[i], new HashSet<Integer>());
                }
                map.get(0).add(0);
                for (int i = 0; i < stones.length; i++) {
                    //当前的jump size
                    for (int k : map.get(stones[i])) {
                        for (int step = k - 1; step <= k + 1; step++) {
                            //代表目标所能到达的位置
                            if (step > 0 && map.containsKey(stones[i] + step)) {
                                map.get(stones[i] + step).add(step);
                            }
                        }
                    }
                }
                return map.get(stones[stones.length - 1]).size() > 0;
            }
        }

    }

    class Dfs {
        public boolean canCross(int[] stones) {
            // 从0开始起跳，目前每次跳0步
            return canCross(stones, 0, 0);
        }

        // 上一步跳了jumpSize步，来到current处，基于此，能否到达终点
        boolean canCross(int[] stones, int currentPos, int jumpSize) {
            /// 枚举出可跳的选择，从下一个石头到最后一个
            for (int i = currentPos + 1; i < stones.length; i++) {
                // 本轮迭代选择跳到i，算出第i石头到当前石头的距离
                int gap = stones[i] - stones[currentPos];
                // 代表可以跨过 i
                if (gap >= jumpSize - 1 && gap <= jumpSize + 1) {
                    // 如果 基于此的递归返回true，说明能到终点
                    if (canCross(stones, i, gap)) {
                        return true;// 递归压栈压到底，如果true了，则true一层向上返回
                    }
                }
            }
            // 考察完当前的所有选项，看看来到的index是不是终点
            return currentPos == stones.length - 1;
        }
    }

    class DfsPlusMemo {
        public boolean canCross(int[] stones) {
            // map代表着这里检查过了
            Map<Integer, Boolean> map = new HashMap<>();
            return canCross(stones, 0, 0, map);
        }

        boolean canCross(int[] stones, int currentPos, int jumpSize, Map<Integer, Boolean> map) {
            int key = currentPos * 1000 + jumpSize;
            if (map.containsKey(key)) {
                return map.get(key);
            }
            for (int i = currentPos + 1; i < stones.length; i++) {
                int gap = stones[i] - stones[currentPos];
                // 代表可以跨过 i
                if (gap >= jumpSize - 1 && gap <= jumpSize + 1) {
                    if (canCross(stones, i, gap, map)) {
                        // 这里更新的是新的key
                        map.put(i * 1000 + gap, true);
                        return true;
                    }
                }
            }
            map.put(key, currentPos == stones.length - 1);
            return currentPos == stones.length - 1;
        }
    }

    public class Solution {
        public boolean canCross(int[] stones) {
            return can_Cross(stones, 0, 0);
        }

        public boolean can_Cross(int[] stones, int ind, int jumpsize) {
            if (ind == stones.length - 1) {
                return true;
            }
            int ind1 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize);
            if (ind1 >= 0 && can_Cross(stones, ind1, jumpsize)) {
                return true;
            }
            int ind2 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize - 1);
            if (ind2 >= 0 && can_Cross(stones, ind2, jumpsize - 1)) {
                return true;
            }
            int ind3 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize + 1);
            if (ind3 >= 0 && can_Cross(stones, ind3, jumpsize + 1)) {
                return true;
            }
            return false;
        }
    }

    class Dp {
        public class Solution {
            public boolean canCross(int[] stones) {
                int[][] memo = new int[stones.length][stones.length];
                for (int[] row : memo) {
                    Arrays.fill(row, -1);
                }
                return can_Cross(stones, 0, 0, memo) == 1;
            }

            public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
                if (memo[ind][jumpsize] >= 0) {
                    return memo[ind][jumpsize];
                }
                for (int i = ind + 1; i < stones.length; i++) {
                    int gap = stones[i] - stones[ind];
                    if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                        if (can_Cross(stones, i, gap, memo) == 1) {
                            memo[ind][gap] = 1;
                            return 1;
                        }
                    }
                }
                memo[ind][jumpsize] = (ind == stones.length - 1) ? 1 : 0;
                return memo[ind][jumpsize];
            }
        }

    }

}
