package interview;

public class MissingNumber1228 {
    /**
     * 添加中间数达到线性
     * 添加中间数达到线性
     * eg1： input：[5，10，20], 5  output: [5,10,15,20]
     *            数组，间隔
     * eg2：input: [5,25,3‍‍‌‍‌‌‌‌‍‍‌‍‌‍‍‍‍‍‌‌5], 10   output:[5,15,25,35]
     * 
     */

    public static void main(String[] args) {
        int[] test1 = new int[] { 5, 10, 20 };
        int[] test2 = new int[] { 5, 25, 35 };
        System.out.println(missingNumber(test1));
        System.out.println(missingNumber(test2));
    }

    // time O(n)
    // space O(1)
    static int missingNumber(int[] arr) {
        int diff = (arr[arr.length - 1] - arr[0]) / arr.length;
        int expected = arr[0];
        for (int i : arr) {
            if (i != expected) {
                return expected;
            }
            expected += diff;
        }
        return arr[0];
    }

    // time O(logn)
    // space O(1)
    static int binearySearch(int[] arr) {
        int l = 0, r = arr.length - 1;
        int diff = (arr[r] - arr[l]) / arr.length;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid] == arr[0] + diff * mid) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return arr[0] + l * diff;
    }
}
