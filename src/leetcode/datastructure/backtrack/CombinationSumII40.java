package leetcode.datastructure.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode-cn.com/problems/combination-sum-ii/
public class CombinationSumII40 {
    
    // 解法和39 题 类似 找到 去重剪枝的关键条件是 candidates[i] = candidates[i - 1] && i > startIndex (保证不在同一层)
    // time O(2^N * N)
    // espace  O(N)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        dfs(candidates, target, 0, path, result);
        return result;
    }


    void dfs(int[] candidates, int target, int startIndex, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                if (i > startIndex && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                path.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }

    }
}
