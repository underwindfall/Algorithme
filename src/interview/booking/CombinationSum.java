package interview.booking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// https://leetcode.cn/problems/combination-sum/
public class CombinationSum {
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            dfs(candidates, 0, candidates.length, new ArrayDeque<>(), ans, target);
            return ans;
        }

        void dfs(int[] candidates, int startIndex, int length, Deque<Integer> path, List<List<Integer>> ans,
                int target) {
            if (target < 0) {
                return;
            }
            if (target == 0) {
                ans.add(new ArrayList<>(path));
                return;
            }
            if (startIndex >= length)
                return;

            for (int i = startIndex; i < length; i++) {
                path.addLast(candidates[i]);
                dfs(candidates, i, length, path, ans, target - candidates[i]);
                path.removeLast();
            }
        }
    }
}
