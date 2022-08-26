package leetcode.algo.backtrack;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/reordered-power-of-2/solution/gong-shui-san-xie-yi-ti-shuang-jie-dfs-c-3s1e/
public class ReorderedPowerOf2 {
    // time O(logN + 1)
    // space O(C)
    public boolean reorderedPowerOf2(int n) {
        Set<Integer> set = new HashSet<>();
        int[] cnts = new int[10];
        for (int i = 1; i < (int) 1e9 + 10; i = i * 2) {
            set.add(i);
        }

        int numberDigists = 0;
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
            numberDigists++;
        }

        return dfs(0, 0, set, numberDigists, cnts);
    }

    private boolean dfs(int index, int curr, Set<Integer> set, int numberDigists, int[] cnts) {
        if (index == numberDigists) {
            return set.contains(curr);
        }

        for (int i = 0; i < 10; i++) {
            if (cnts[i] != 0) {
                cnts[i]--;
                if ((i != 0 || curr != 0) && dfs(index + 1, curr * 10 + i, set, numberDigists, cnts)) {
                    return true;
                }
                cnts[i]++;
            }
        }
        return false;
    }
}
