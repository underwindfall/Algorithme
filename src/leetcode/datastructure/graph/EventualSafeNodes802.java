package leetcode.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/find-eventual-safe-states/
public class EventualSafeNodes802 {
    // time O(V+E)
    // espace O(V+E)

    private int[][] g;
    private int n;

    private enum State {
        UNKNWON, VISITIING, UNSAFE, SAFE
    }

    private State states[];

    public List<Integer> eventualSafeNodes(int[][] graph) {
        g = graph;
        n = g.length;
        states = new State[n];
        for (int i = 0; i < n; i++) {
            states[i] = State.UNKNWON;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(i) == State.SAFE) {
                ans.add(i);
            }
        }
        ans.sort((a, b) -> a - b);
        return ans;
    }

    State dfs(int cur) {
        if (states[cur] == State.VISITIING) {
            return states[cur] = State.UNSAFE;
        }
        if (states[cur] != State.UNKNWON) {
            return states[cur];
        }
        for (int nxt : g[cur]) {
            if (dfs(nxt) == State.UNSAFE) {
                return states[cur] = State.UNSAFE;
            }
        }
        return states[cur] = State.SAFE;
    }
}
