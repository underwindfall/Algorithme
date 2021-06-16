package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

//https://leetcode-cn.com/problems/subsets-ii/
public class SubsetsII90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, path, result);
        return result;
    }


    void dfs(int[] nums, int startIndex, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}
