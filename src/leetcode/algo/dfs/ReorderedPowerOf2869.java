package leetcode.algo.dfs;

import java.util.Set;
import java.util.HashSet;

// https://leetcode-cn.com/problems/reordered-power-of-2/
public class ReorderedPowerOf2869 {
    public boolean reorderedPowerOf2(int n) {
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= (int) 1e9; i *= 2) {
            set.add(i);
        }

        int[] count = new int[10];
        int numLen = 0;
        while (n != 0) {
            count[n % 10]++;
            n = n / 10;
            numLen++;
        }

        return dfs(0, numLen, 0, count, set);
    }

    boolean dfs(int index, int max, int currNum, int[] count, Set<Integer> set) {
        if (index == max) {
            return set.contains(currNum);
        }
        for (int i = 0; i < 10; i++) {
            if (count[i] != 0) {
                count[i]--;
                if ((i != 0 || currNum != 0) && dfs(index + 1, max, currNum * 10 + i, count, set)) {
                    return true;
                }
                count[i]++;
            }
        }
        return false;
    }
}
