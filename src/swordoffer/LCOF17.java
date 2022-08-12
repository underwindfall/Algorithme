package swordoffer;

// https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/   
public class LCOF17 {

    // time O(10^n)
    // space O(10^n)
    class DFS {
        StringBuilder res;
        int nine = 0, count = 0, start, n;
        char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public int[] printNumbers(int n) {
            this.n = n;
            res = new StringBuilder();
            num = new char[n];
            start = n - 1;
            dfs(0);
            res.deleteCharAt(res.length() - 1);
            return res.toString();
        }


        void dfs(int n) {
                
        }
    }

    // time O(n)
    // space O(1)
    class Bad {
        public int[] printNumbers(int n) {
            int max = (int) (Math.pow(10, n)) - 1;
            int[] ans = new int[max];
            for (int i = 0; i < max; i++) {
                ans[i] = i + 1;
            }
            return ans;
        }
    }
}
