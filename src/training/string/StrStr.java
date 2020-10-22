package training.string;

public class StrStr {

    // 滑动窗口
    static int strStr(String haystack, String needle) {
        int N = haystack.length() - 1, L = needle.length() - 1;
        for (int i = 0; i < N - L + 1; i++) {
            if (haystack.substring(i, i + L + 1).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // 双指针
    static int strStr1(String haystack, String needle) {
        int L = needle.length(), N = haystack.length();
        if (L == 0)
            return 0;
        int pn = 0;
        while (pn < N - L + 1) {
            // 找到第一个match的点
            while (pn < N - L + 1 && haystack.charAt(pn) != needle.charAt(0)) {
                ++pn;
            }
            // 循环到整个最大的字符串匹配度
            int curLen = 0, pL = 0;
            while (pL < L && pn < N && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++curLen;
            }
            // if the whole needle string is found,
            // return its start position
            if (curLen == L)
                return pn - L;

            // otherwise, backtrack
            pn = pn - curLen + 1;
        }

        return -1;

    }


    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
        System.out.println(strStr(haystack, needle));
        System.out.println(strStr1(haystack, needle));
    }
}
