package leetcode.algo.greedy;

// https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/s
public class WinnerOfGames2038 {
    // time O(n)
    // space O(1)
    // 贪心原则， B 和 A互不影响
    public boolean winnerOfGame(String colors) {
        char[] cs = colors.toCharArray();
        int n = cs.length;
        int a = 0, b = 0;
        for (int i = 1; i < n - 1; i++) {
            if (cs[i] == 'A' && cs[i - 1] == 'A' && cs[i + 1] == 'A')
                a++;
            if (cs[i] == 'B' && cs[i - 1] == 'B' && cs[i + 1] == 'B')
                b++;
        }
        return a > b;
    }
}
