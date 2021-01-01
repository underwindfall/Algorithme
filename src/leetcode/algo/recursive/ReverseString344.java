package leetcode.algo.recursive;

// https://leetcode-cn.com/problems/reverse-string/solution/
public class ReverseString344 {
    class DoubleIndex {
        public void reverseString(char[] s) {
            int i = 0, j = s.length - 1;
            while (i <= j) {
                char tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
                i++;
                j--;
            }
        }
    }

    class Recursive {
        public void reverseString(char[] s) {
            helper(0, s.length - 1, s);
        }

        void helper(int left, int right, char[] s) {
            if (left >= right)
                return;
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            helper(left + 1, right - 1, s);
        }
    }
}
