package leetcode.algo.sort.classic;

import java.util.Arrays;

//快速排序算法
// O(N * logn N)
/**
 * 从数列中挑出一个元素，称为 "基准"（pivot）;
 * 
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 */
public class QuickSort {
    public void sort(int[] sourceArray) {
        // 对arr 进行拷贝 不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        quickSort(arr);
    }

    // public int[] sort2(int[] sourceArray) {
    //     // 对arr 进行拷贝 不改变参数内容
    //     int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
    //     return quickSort2(arr, 0, arr.length - 1);
    // }

    void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    void qsort(int[] arr, int low, int high) {
        if (low >= high)
            return;
        int pivot = partition(arr, low, high); // 将数组分为两部分
        qsort(arr, low, pivot - 1); // 递归排序左子数组
        qsort(arr, pivot + 1, high); // 递归排序右子数组
    }

    int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // 基准
        while (low < high) {
            while (low < high && arr[high] >= pivot)
                --high;
            arr[low] = arr[high]; // 交换比基准大的记录到左端
            while (low < high && arr[low] <= pivot)
                ++low;
            arr[high] = arr[low]; // 交换比基准小的记录到右端
        }
        // 扫描完成，基准到位
        arr[low] = pivot;
        // 返回的是基准的位置
        return low;
    }

    // int[] quickSort2(int[] arr, int left, int right) {
    //     int i = left, j = right;
    //     // arr[l] 作为pivot
    //     while (i < j) {
    //         while (i < j && arr[i] <= arr[left]) {
    //             i++;
    //         }
    //         while (i < j && arr[j] >= arr[left]) {
    //             j--;
    //         }
    //         swap(arr, i, j);
    //     }
    //     swap(arr, i, left);
    //     if (i > left) {
    //         return quickSort(arr, left, i - 1);
    //     }
    //     if (i < left) {
    //         return quickSort(arr, i + 1, right);
    //     }
    //     return arr;

    // }

    /*
     * This function takes last element as pivot, places the pivot element at its
     * correct position in sorted array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     */
    // int partition(int[] arr, int left, int right) {
    //     // 设置基准值
    //     int pivot = left;
    //     int index = pivot + 1;
    //     for (int i = index; i <= right; i++) {
    //         if (arr[i] < arr[pivot]) {
    //             swap(arr, i, index);
    //             index++;
    //         }
    //     }
    //     swap(arr, pivot, index - 1);
    //     return index - 1;
    // }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] test = new int[] { 3, 4, 2, 7, 9, 2 };
        QuickSort quickSort = new QuickSort();
        quickSort.sort(test);
        System.out.println(Arrays.toString(test));
        // System.out.println(Arrays.toString(quickSort.sort2(test)));
    }
}
