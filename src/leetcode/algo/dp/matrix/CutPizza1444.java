package leetcode.algo.dp.matrix;

// https://leetcode-cn.com/problems/number-of-ways-of-cutting-a-pizza/
public class CutPizza1444 {
    // 每次切剩下的都是右下角小矩阵    
    class Dp {
        public final int MOD = 1000000007;

        public int ways(String[] pizza, int k) {
            int rol = pizza.length;
            int col = pizza[0].length();
            int[][] map = new int[rol + 1][col + 1];
            for (int i = rol - 1; i >= 0; i--) {
                for (int j = col - 1; j >= 0; j--) {
                    // map[i][j] 用来计算 以 i, j开始到最后的区域里面 有多少的水果 
                    map[i][j] = map[i + 1][j] + map[i][j + 1] - map[i + 1][j + 1];
                    if (pizza[i].charAt(j) == 'A') {
                        map[i][j]++;
                    }
                }
            }
            // dp k -> 次数 rol 做结尾 col 做结尾
            int[][][] dp = new int[k][rol][col];
            dp[0][0][0] = 1;
            for (int n = 1; n < k; n++) { // 切n次
                for (int i = 0; i < rol; i++) {
                    for (int j = 0; j < col; j++) {
                        // 剩下i , j
                        int count = 0;
                        for (int l = 0; l < i; l++) { // 横着切
                            int flag = map[l][j] - map[i][j];
                            if (flag != 0 && map[i][j] != 0) {
                                count += dp[n - 1][l][j];
                                count %= MOD;
                            }
                        }
                        for (int l = 0; l < j; l++) { // 竖着切
                            int flag = map[i][l] - map[i][j];
                            if (flag != 0 && map[i][j] != 0) {
                                count += dp[n - 1][i][l];
                                count %= MOD;
                            }
                        }
                        dp[n][i][j] = count;
                    }
                }
            }
            int res = 0;
            for (int i = 0; i < rol; i++) {
                for (int j = 0; j < col; j++) {
                    res += dp[k - 1][i][j];
                    res %= MOD;
                }
            }
            return res;
        }
    }

    class Recursive {
        public int ways(String[] pizza, int k) {
            if (pizza == null || pizza.length == 0 || pizza[0] == null || pizza[0].length() == 0 || k <= 0)
                return 0;
            return count(pizza, 0, 0, k - 1);
        }

        private int count(String[] pizza, int row, int col, int k) {
            if (k == 0) {
                for (int r = row; r < pizza.length; r++) {
                    if (pizza[r].substring(col).indexOf('A') != -1)
                        return 1;
                }
                return 0;
            }
            int ri = row, ci = col;
            // 找最上面有苹果的行
            while (ri < pizza.length) {
                if (pizza[ri].substring(col).indexOf('A') == -1)
                    ri++;
                else
                    break;
            }
            // 找最左边有苹果的列
            while (ci < pizza[0].length()) {
                boolean hasA = false;
                // 已经求得最上边有苹果的行，就从这里开始，可以省点事
                int r = ri;
                while (r < pizza.length) {
                    if (pizza[r].charAt(ci) == 'A') {
                        hasA = true;
                        break;
                    }
                    r++;
                }
                if (hasA)
                    break;
                else
                    ci++;
            }
            int res = 0;
            // 横着切
            for (int i = ri + 1; i < pizza.length; i++) {
                res = (res + count(pizza, i, ci, k - 1)) % 1000000007;
            }
            // 竖着切
            for (int i = ci + 1; i < pizza[0].length(); i++) {
                res = (res + count(pizza, ri, i, k - 1)) % 1000000007;
            }
            return res;
        }
    }

}
