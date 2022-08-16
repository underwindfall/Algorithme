package interview;

// https://mp.weixin.qq.com/s/XcKQwnwCh5nZsz-DLHJwzQ
class _36BitsAdd {

    String ad36Strings(String num1, String num2) {
        int carry = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        int x, y;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0) {
            x = i >= 0 ? getInt(num1.charAt(i)) : 0;
            y = j >= 0 ? getInt(num2.charAt(j)) : 0;
            int temp = x + y + carry;
            res.append(getChar(temp % 36));
            carry = temp / 36;
            i--;
            j--;
        }
        return res.reverse().toString();
    }

    int getInt(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        } else {
            return ch - 'a';
        }
    }

    char getChar(int n) {
        if (n <= 9) {
            return (char) (n + '0');
        } else {
            return (char) (n - 10 + 'a');
        }
    }
}