package leetcode.algo.doubleindex;

// https://leetcode-cn.com/problems/next-greater-element-iii
public class NextGreaterElementIII556 {
    // time O(n)
    // space O(n)
    public int nextGreaterElement(int n) {
        char[] a = ("" + n).toCharArray();
        int i = a.length - 2;
        // 递增数列 找到第一个小于i + 1 > i的数字
        while (i >= 0 && a[i + 1] <= a[i]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = a.length - 1;
        while (j >= 0 && a[j] <= a[i]) {
            j--;
        }

        swap(a, i, j);
        reverse(a, i + 1);
        try {
            return Integer.parseInt(new String(a));
        } catch (Exception e) {
            return -1;
        }
    }

    void reverse(char[] a, int start) {
        int i = start, j = a.length - 1;
        while (i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
    }

    void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
