package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
class NextGreatestLetter744 {

    public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (letters[mi] <= target)
                lo = mi + 1;
            else
                hi = mi;
        }
        return letters[lo % letters.length];
    }

}