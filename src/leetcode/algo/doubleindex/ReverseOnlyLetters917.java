package leetcode.algo.doubleindex;

// https://leetcode-cn.com/problems/reverse-only-letters/
public class ReverseOnlyLetters917 {
    //time O(n)
    //space O(1)
    public String reverseOnlyLetters(String s) {
        int lo = 0, ho = s.length() - 1;
        char[] ch = s.toCharArray();
        while (lo < ho) {
            while (lo < ho && !Character.isLetter(ch[lo])) {
                lo++;
            }
            while (lo < ho && !Character.isLetter(ch[ho])) {
                ho--;
            }
            swap(ch, lo, ho);
            lo++;
            ho--;
        }
        return new String(ch);
    }

    void swap(char[] arr, int i , int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
