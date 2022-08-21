package leetcode.algo.doubleindex;

// https://leetcode.cn/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
public class IsPrefixOfWord1455 {
    // time O(N)
    // space O(1)
    public int isPrefixOfWord(String sentence, String searchWord) {
        int n = sentence.length(), index = 1, start = 0, end = 0;
        while (start < n) {
            while (end < n && sentence.charAt(end) != ' ') {
                end++;
            }
            if (isPrefix(sentence, start, end, searchWord)) {
                return index;
            }

            index++;
            end++;
            start = end;
        }
        return -1;
    }

    boolean isPrefix(String sentence, int start, int end, String searchWord) {
        for (int i = 0; i < searchWord.length(); i++) {
            if (start + i >= end || sentence.charAt(start + i) != searchWord.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
