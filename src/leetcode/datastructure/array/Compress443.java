package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/string-compression/
public class Compress443 {
    // time O(n)
    // space O(1)
    public int compress(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }

    void reverse(char[] input, int left, int right) {
        while (left < right) {
            char tmp = input[left];
            input[left] = input[right];
            input[right] = tmp;
        }
    }
}
