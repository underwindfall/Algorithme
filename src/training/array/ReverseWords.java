package training.array;

//https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 
 *  
 * 
 * 示例：
 * 
 * 输入："Let's take LeetCode contest" 输出："s'teL ekat edoCteeL tsetnoc"  
 * 
 * 提示：
 * 
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class ReverseWords {
    // time:O(N*M)
    // espace:O(NM)
    static String reverseWords(String s) {
        String[] array = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            char[] word = array[i].toCharArray();
            for (int j = 0; j < word.length / 2; j++) {
                char tmp = word[j];
                word[j] = word[word.length - j - 1];
                word[word.length - j - 1] = tmp;
            }
            result.append(String.valueOf(word));
            if (i != array.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    static String reverseWords1(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                stringBuffer.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                stringBuffer.append(' ');
            }
        }
        return stringBuffer.toString();
    }

    // 双指针
    // time: O(N*m) N 是字符串长度 m是每个小word的最大word长度
    // espeace: O(N)
    static String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chs = s.toCharArray();
        int slow = 0;
        for (int i = 0; i < chs.length && slow < chs.length; i++) {
            // 循环到word 最后一个字符是不为空的时候
            if (chs[i] != ' ' && (i + 1 >= chs.length || chs[i + 1] == ' ')) {
                reverseWord(chs, slow, i);
                slow = i + 2;
            }
        }
        return new String(chs);
    }

    private static void reverseWord(char[] chs, int slow, int i) {
        while (slow < i) {
            char tmp = chs[slow];
            chs[slow] = chs[i];
            chs[i] = tmp;
            slow++;
            i--;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
        System.out.println(reverseWords1("Let's take LeetCode contest"));
        System.out.println(reverseWords2("Let's take LeetCode contest"));
    }
}
