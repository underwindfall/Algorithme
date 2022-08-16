package leetcode.algo.greedy;

import java.util.Arrays;

// https://leetcode.cn/problems/largest-number/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-vn86e/
public class LargestNumber179 {
    // time O(N^2)
    // space O(N)
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n; i++)
            ss[i] = "" + nums[i];
        Arrays.sort(ss, (a, b) -> {
            String sa = a + b, sb = b + a;
            return sb.compareTo(sa);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : ss)
            sb.append(s);
        int len = sb.length();
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0')
            k++;
        return sb.substring(k);
    }
}
