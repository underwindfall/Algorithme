package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/longest-common-prefix/
public class LongestCommonPrefix14 {

    // 思路1: 解决这个题目的方法是两个进行比较
    // 若果有相同的下一个然后知道循环到最后一个单词
    // time : O(N*M) M->单词平均长度 N 单词数量
    // espace: O(1)
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String common = strs[0];
        for (int i = 0; i < strs.length; i++) {

            int commonLength = Math.min(common.length(), strs[i].length());
            int index = 0;
            while (index < commonLength && common.charAt(index) == strs[i].charAt(index)) {
                index++;
            }
            common = common.substring(0, index);

        }
        return common;
    }

    /**
     * 思想二 纵向的查找这些信息，并且通过比较二维数组的数据来找到最大公解
     * 时间复杂度：O(mn)O(mn)，其中 mm 是字符串数组中的字符串的平均长度，nn 是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次
     * espace: O(1)
     * @param strs
     * @return
     */
    public String longestCommonPrefixVertical(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }

    
    // 思路三 二分法解决
    // 拿第一个单词作为标准
    // 他的长度决定了公共开头的单词的length
    // 如果mid可以让每个单词都满足条件 mid+1
    // else mid -1
    //time:O(logN*M*N) n 第一个单词长度
    //espace:O(1)
    public String longestCommonPrefixBinarySearch(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int start = 0;
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int end = minLength;

        int count = strs.length;
        while (start < end) {
            boolean found = true;
            int mid = (end - start + 1) / 2 + start;
            String str = strs[0].substring(0, mid);
            for (int i = 1; i < count; i++) {
                String nextStr = strs[i];
                for (int j = 0; j < mid; j++) {
                    if (str.charAt(j) != nextStr.charAt(j)) {
                        found = false;
                    }
                }
            }
            if (found) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return strs[0].substring(0, start);
    }

}
