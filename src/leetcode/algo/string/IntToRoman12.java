package leetcode.algo.string;

//https://leetcode-cn.com/problems/integer-to-roman/
public class IntToRoman12 {
    // time O(1)
    // space O(1)
    class Hardcode {
        final String[] thousands = { "", "M", "MM", "MMM" };
        final String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        final String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        final String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

        public String intToRoman(int num) {
            // 5, 10. [4, 5) [9, 10)
            // 50, 100 [40-50) [90, 100)
            // 500, 1000 [400 - 500) [900, 1000)

            // 1<= num <= 3999
            return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10 / 1];
        }
    }
    // time O(1)
    // space O(1)
    class Simultation {
        final int vals[] = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        final String symbols[] = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        public String intToRoman(int num) {
            // 5, 10. [4, 5) [9, 10)
            // 50, 100 [40-50) [90, 100)
            // 500, 1000 [400 - 500) [900, 1000)

            // 1 <= num <= 3999
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < vals.length; i++) {
                if (num >= vals[i]) {
                    int n = num / vals[i];
                    num -= vals[i] * n;
                    sb.append(symbols[i].repeat(n));
                    if (n == 0) {
                        break;
                    }
                }

            }
            return sb.toString();
        }
    }

}
