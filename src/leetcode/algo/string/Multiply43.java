package leetcode.algo.string;

//time O(m * n + n^2)
//space O(m + n)
//https://leetcode-cn.com/problems/multiply-strings/
public class Multiply43 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String ans = "0";

        int m = num1.length(), n = num2.length();

        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';

                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }

            if (add != 0) {
                curr.append(add % 10);
            }

            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }   


    String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int add = 0;
        StringBuffer sb = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0? num1.charAt(i) - '0' : 0;
            int y = j >= 0? num2.charAt(j) - '0' : 0;
            int res = x + y + add;
            sb.append(res % 10);
            add = res / 10;
            i--;
            j--;
        }
        sb.reverse();
        return sb.toString();
    }
}
