package leetcode.algo.string;

//https://leetcode-cn.com/problems/compare-version-numbers/
public class CompareVersion165 {
    //time O(n + m)
    //space O(n + m)
    class Simulation {
        public int compareVersion(String v1, String v2) {
            String[] ss1 = v1.split("\\."), ss2 = v2.split("\\.");
            int n = ss1.length, m = ss2.length;
            int i = 0, j = 0;
            while (i < n || j < m) {
                int a = 0, b = 0;
                if (i < n)
                    a = Integer.parseInt(ss1[i++]);
                if (j < m)
                    b = Integer.parseInt(ss2[j++]);
                if (a != b)
                    return a > b ? 1 : -1;
            }
            return 0;
        }
    }
}
