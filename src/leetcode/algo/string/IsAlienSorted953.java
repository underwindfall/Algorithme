package leetcode.algo.string;

// https://leetcode.cn/problems/verifying-an-alien-dictionary/
public class IsAlienSorted953 {
    //time O(n * word length)
    //space O(1)
    public boolean isAlienSorted(String[] words, String order) {
        int[] orderded = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orderded[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                // 如果第一个word长度超过第二个文化直接跳过
                if (i + 1 < words.length && words[i].length() > words[i + 1].length()) {
                    return false;
                }

                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    int currentWordChar = words[i].charAt(j) - 'a';
                    int nextWordChar = words[i + 1].charAt(j) - 'a';
                    if (orderded[currentWordChar] > orderded[nextWordChar]) return false;
                    //剩下的不用检查
                    else break;
                }
            }
        }
        return true;
    }
}