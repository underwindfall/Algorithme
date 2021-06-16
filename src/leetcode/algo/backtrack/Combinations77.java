package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/combinations/
public class Combinations77 {
    // time
    // espace
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(n, k, 1, path, result);
        return result;
    }

    private void dfs(int n, int k, int startIndex, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < n + 1; i++) {
            path.add(i);
            dfs(n, k, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}
