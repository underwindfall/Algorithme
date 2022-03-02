package leetcode.algo.string;

//https://leetcode-cn.com/problems/find-the-closest-palindrome/
public class NearestPalindromic564 {
    // https://leetcode.com/problems/find-the-closest-palindrome/discuss/147949/Thinking-Process
    // 1. 12321 取 字符的一半 123 bigger 124 smaller 122 -> 12421 12221
    // 2. 123321 取 字符的一半 123 bigger 124 smaller 122 -> 124421 122221
    // 3. 12345 取 字符的一半 123 bigger 124 smaller 122 要多考虑一种情况 123 -> 12421 12221 12321

    // 4. corners cases
    // 4 - 1 case 1. <= 10, OR equal to 100, 1000, 10000, We simply decrease n by 1.
    // 4 - 2 case 2. 11, 101, 1001, 10001, 100001, ... We simply decrease n by 2.
    // 4 - 3 case 3 9, 999, 9999, 99999, ... We simply increase n by 2.
    public String nearestPalindromic(String n) {
        long nl = Long.parseLong(n);
        int len = n.length();
        // corners cases
        // case 1. <= 10, OR equal to 100, 1000, 10000, We simply decrease n by 1.
        if (nl <= 10 || (nl % 10 == 0) && (n.charAt(0) == '1') && Long.parseLong(n.substring(1)) == 0) {
            return (nl - 1) + "";
        }

        // 11, 101, 1001, 10001, 100001
        if (nl == 1 || (nl % 10 == 1 && n.charAt(0) == '1' && Long.parseLong(n.substring(1, len - 1)) == 0)) {
            return (nl - 2) + "";
        }
        // 9, 999, 9999, 99999
        if (isAllDigitNine(n)) {
            return (nl + 2) + "";
        }
        //
        // Construct the closest palindrome and calculate absolute difference with n
        //
        boolean isEvenDigit = len % 2 == 0;
        String palindromeRootStr = (isEvenDigit) ? n.substring(0, len / 2) : n.substring(0, len / 2 + 1);
        int palindromeRoot = Integer.parseInt(palindromeRootStr);
        long equal = toPalindromeDigits(palindromeRoot + "", isEvenDigit);
        // 计算出新生成的equal 和nl 区别
        long diffEqual = Math.abs(equal - nl);

        long bigger = toPalindromeDigits("" + (palindromeRoot + 1), isEvenDigit);
        long diffBigger = Math.abs(nl - bigger);

        long smaller = toPalindromeDigits("" + (palindromeRoot - 1), isEvenDigit);
        long diffSmaller = Math.abs(nl - smaller);

        //
        // Find the palindrome with minimum absolute differences
        // If tie, return the smaller one
        //
        long closest = (diffBigger < diffSmaller) ? bigger : smaller;
        long minDiff = Math.min(diffBigger, diffSmaller);

        if (diffEqual != 0) { // n is not a palindrome, diffEqual should be considered
            if (diffEqual == minDiff) { // if tie
                closest = Math.min(equal, closest);
            } else if (diffEqual < minDiff) {
                closest = equal;
            }
        }
        
        return "" + closest;
    }

    long toPalindromeDigits(String num, boolean isEvenDigit) {
        StringBuilder reversedNum = new StringBuilder(num).reverse();
        String palindromeDigits = isEvenDigit ? num + reversedNum.toString()
                : num + (reversedNum.deleteCharAt(0)).toString();
        return Long.parseLong(palindromeDigits);
    }

    boolean isAllDigitNine(String n) {
        for (char ch : n.toCharArray()) {
            if (ch != '9') {
                return false;
            }
        }
        return true;
    }
}
