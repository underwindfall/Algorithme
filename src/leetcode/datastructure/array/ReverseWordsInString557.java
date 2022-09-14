package leetcode.datastructure.array;

// https://leetcode.cn/problems/reverse-words-in-a-string-iii/
public class ReverseWordsInString557 {
    // 快慢指针
    // fast 循环单词 当遇到空格的时候停下 让slow向前走一步
    // slow 从头开始
    // time O(N^2/2)
    // espace O(1)
    public String reverseWords(String s) {
        int slow = 0;
        char[] wordArray = s.toCharArray();
        for (int fast = 0; fast < wordArray.length; fast++) {
            if (wordArray[fast] != ' ' && (fast + 1 >= wordArray.length || wordArray[fast + 1] == ' ')) {
                // 翻转操作
                int wordStart = slow;
                int wordEnd = fast;
                while (wordStart < wordEnd) {
                    char tmp = wordArray[wordStart];
                    wordArray[wordStart] = wordArray[wordEnd];
                    wordArray[wordEnd] = tmp;
                    wordStart++;
                    wordEnd--;
                }
                slow = fast + 2;
            }
        }
        return new String(wordArray);
    }
}
