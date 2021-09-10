package leetcode.algo.recursive;

// https://leetcode-cn.com/problems/count-and-say/
public class CountAndSay38 {
    // time O(n)
    // space O(n)
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        } else {
            String lastStr = countAndSay(n - 1); // 1 2 1 1
            StringBuilder ans = new StringBuilder();
            int i = 0, j = 1, len = lastStr.length();
            while (j < len) {
                if (lastStr.charAt(i) != lastStr.charAt(j)) {
                    ans.append(j - i).append(lastStr.charAt(i));
                    i = j;
                }
                j++;
            }
            ans.append(j - i).append(lastStr.charAt(i));
            return ans.toString();
        }

    }
}
