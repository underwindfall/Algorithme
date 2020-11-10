package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/reverse-string/
public class ReverseString344 {
    // 双指针
    // time O(N/2) => O(N)
    // espace:O(1)
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i <= j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
