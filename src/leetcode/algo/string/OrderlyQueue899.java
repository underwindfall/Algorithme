package leetcode.algo.string;

import java.util.Arrays;

// https://leetcode.cn/problems/orderly-queue/
public class OrderlyQueue899 {
    // time O(n) || O（N * logN)
    // space O(1)
    public String orderlyQueue(String s, int k) {
        // 模拟一个元素往后移动
        if (k == 1) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 1; i < s.length(); i++) {
                // 第一个元素移动到最后
                sb.append(sb.charAt(0)).deleteCharAt(0);
                if (sb.toString().compareTo(s) < 0) {
                    s = sb.toString();
                }
            }
            return s;
        } else {
            // 直接排序
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }
}
