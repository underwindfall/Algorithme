package leetcode.algo.sort;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/lexicographical-numbers/
public class LexicalOrder386 {
    // time O(10^N)
    //space O(N)
    List<Integer> ans = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        for (int i = 1; i <= 9; i++)
            dfs(i, n);
        return ans;
    }

    void dfs(int cur, int limit) {
        if (cur > limit)
            return;
        ans.add(cur);
        for (int i = 0; i <= 9; i++)
            dfs(cur * 10 + i, limit);
    }
}
