package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/iterator-for-combination/
public class CombinationIterator1286 {
    // time O(2^n)
    // space O(1)
    class CombinationIterator {
        private List<String> list = new ArrayList<>();
        private int index = 0;

        public CombinationIterator(String characters, int combinationLength) {
            dfs(0, combinationLength, new StringBuilder(), characters);
        }

        // 顺序获取元素
        public String next() {
            return list.get(index++);
        }

        // 是否遍历完成
        public boolean hasNext() {
            return index < list.size();
        }

        // 回溯算法，使用StringBuilder记录结果，递归完成后从后往前删除
        private void dfs(int start, int target, StringBuilder sb, String s) {
            if (sb.length() == target) {
                list.add(sb.toString());
                return;
            }
            for (int i = start; i < s.length(); i++) {
                sb.append(s.charAt(i));
                dfs(i + 1, target, sb, s);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
