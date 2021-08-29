package leetcode.algo.divideandconquer;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/beautiful-array
//https://leetcode-cn.com/problems/beautiful-array/solution/932-piao-liang-shu-zu-fen-zhi-si-xiang-g-1xxg/
public class BeautifulArray932 {
    // time O(n *logn)
    // space O(n *logn)
    Map<Integer, int[]> memo;

    public int[] beautifulArray(int N) {
        memo = new HashMap<>();
        memo.put(1, new int[] { 1 });
        return f(N);
    }

    private int[] f(int N) {
        if (!memo.containsKey(N)) {
            int index = 0;
            int[] res = new int[N];
            for (int x : f((N + 1) / 2)) {
                res[index++] = 2 * x - 1;
            }
            for (int x : f(N / 2)) {
                res[index++] = 2 * x;
            }
            memo.put(N, res);
        }
        return memo.get(N);
    }
}
