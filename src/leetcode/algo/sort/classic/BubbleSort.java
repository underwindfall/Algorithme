package leetcode.algo.sort.classic;

public class BubbleSort {
    /**
     * 冒泡排序 222ms
     * 遍历n次，每次两两比较，如果存在左值小于右值，则交换左右值
     * 加入flag判断，提前终止循环，时间116ms
     */
    // time O(N^2)
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = arr.length - 1; i > 0; i--) { // 每次需要排序的长度
            for (int j = 0; j < i; j++) { // 从第一个元素到第i个元素
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            } // loop j
        } // loop i
    }

    //time O(N)
    void bubbleSortOptimization(int[] arr) {
        int temp = 0;
        boolean swap;
        for (int i = arr.length - 1; i > 0; i--) { // 每次需要排序的长度
            swap = false;
            for (int j = 0; j < i; j++) { // 从第一个元素到第i个元素
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            } // loop j
            if (swap == false) {
                break;
            }
        }
    }
}
