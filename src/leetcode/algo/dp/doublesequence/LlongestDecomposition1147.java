package leetcode.algo.dp.doublesequence;

//https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/
public class LlongestDecomposition1147 {
    int [][]memo;
    public int longestDecomposition(String text) {
        int n = text.length();
        memo = new int[n][n];
        return dfs(text, 0, n-1);
    }

    int dfs(String text, int l, int r) {
        //APA -> P 最开始的值就是1
        if(l == r) return 1;
        if(l > r) return 0;
        if(memo[l][r] > 0) return memo[l][r];
        int x = (r - l + 1) / 2 ;
        //计算子串的长度
        int m = 0;
        int intial = 1;
        for (int i = 1; i <= x; i++) {
            //java substring include head not tail
            if (text.substring(l, l + i).equals(text.substring(r-i+1, r+1))) {
                m = Math.max(m, dfs(text, l+i, r-i));
                intial = 2;
                break; // 如果AAPAA，那么中间的APA可以组成新的P，所以可以找到最短的相等前缀字串和后缀字串后就直接break
            }
        }

        memo[l][r] = m+intial;
        return memo[l][r]; 
    }
}
