
package leetcode.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode-cn.com/problems/partition-labels/
public class PartitionLabels763 {
    //time O(n)
    //space O(1)
    //题目求解 将s尽可能最最大化划分。 划分条件是 每个部分含有尽可能多同一个字符
    //思路 就是纪律当前char c的最后位置出现的index 并且通过这个记录划分
    public List<Integer> partitionLabels(String s) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a'] = i;
        }
        int start = 0, scannedCharMaxPos = 0;

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            int currentCharMaxPos = map[s.charAt(i) - 'a'];
            scannedCharMaxPos = Math.max(scannedCharMaxPos, currentCharMaxPos);
            if (i == scannedCharMaxPos) {
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }
}
