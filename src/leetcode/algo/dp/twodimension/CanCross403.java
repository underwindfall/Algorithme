package leetcode.algo.dp.twodimension;

import java.util.Arrays;

// https://leetcode-cn.com/problems/frog-jump/
public class CanCross403 {
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
