package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/beautiful-arrangement/
@SuppressWarnings("unchecked")
public class BeatifulArrangment526 {
    // time O(N!)
    // space O(N!)
    boolean[] vis;
    List<Integer>[] match;
    int num;

    public int countArrangement(int n) {
        vis = new boolean[n + 1];
        match = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        backtrack(1, n);
        return num;
    }

    void backtrack(int index, int n) {
        if (index == n + 1) {
            num++;
            return;
        }

        for (int x : match[index]) {
            if (!vis[x]) {
                vis[x] = true;
                backtrack(index + 1, n);
                vis[x] = false;
            }
        }
    }
}