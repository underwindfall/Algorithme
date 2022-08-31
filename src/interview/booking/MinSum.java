package interview.booking;

import java.util.PriorityQueue;

// https://www.geeksforgeeks.org/minimize-sum-of-an-array-by-at-most-k-reductions/
// Given an array of integers, perform some number k of operations. Each operation consists of
// removing an element from the array, dividing it by 2 and inserting the ceiling of that result back
// into the array. Minimize the sum of the elements in the final array.
public class MinSum {
    public int minSum(int a[], int n, int k) {
        // Implements the MaxHeap
        PriorityQueue<Integer> maxheap = new PriorityQueue<>((one, two) -> two - one);

        // Insert elements into the MaxHeap
        for (int i = 0; i < n; i++)
            maxheap.add(a[i]);

        while (maxheap.size() > 0 && k > 0) {

            // Remove the maximum
            int max_ele = maxheap.poll();

            // Insert maximum / 2
            maxheap.add(max_ele / 2);
            k -= 1;
        }

        // Stores the sum of remaining elements

        int sum = 0;
        while (maxheap.size() > 0)
            sum += maxheap.poll();

        return sum;
    }
}
