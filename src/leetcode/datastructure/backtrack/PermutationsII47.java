package leetcode.datastructure.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

//https://leetcode-cn.com/problems/permutations-ii/
public class PermutationsII47 {
    // backtracking
    // time O(N * N!)
    // espace O(N * N!)
    //排序过后 nums[i] == nums[i - 1] 相同的数字要剪纸避免重复计算，同时要注意的是!used[i - 1] 条件 否则在同一个分支上应该仍是满足的
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        dfsbacktrack(nums, len, 0, used, path, res);
        return res;
    }

    private void dfsbacktrack(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfsbacktrack(nums, len, depth + 1, used, path, res);
            // 回溯代码
            used[i] = false;
            path.removeLast();
        }
    }
}
