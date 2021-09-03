package leetcode.datastructure.heap;

import java.util.PriorityQueue;
import java.util.TreeMap;

//https://leetcode-cn.com/problems/find-median-from-data-stream/
public class MedianFinder295 {
    // time O(logN)
    // space O(n)
    class Heap {
        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public Heap() {
            queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
            queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }

    class ArrayHeap {
        TreeMap<Integer, Integer> head = new TreeMap<>(), tail = new TreeMap<>();
        int[] arr = new int[101];
        int a, b, c;

        ArrayHeap() {

        }

        public void addNum(int num) {
            if (num >= 0 && num <= 100) {
                arr[num]++;
                b++;
            } else if (num < 0) {
                head.put(num, head.getOrDefault(num, 0) + 1);
                a++;
            } else if (num > 100) {
                tail.put(num, tail.getOrDefault(num, 0) + 1);
                c++;
            }
        }

        public double findMedian() {
            int size = a + b + c;
            if (size % 2 == 0) {
                return (find(size / 2) + find(size / 2 + 1)) / 2.0;
            } else {
                return find(size / 2 + 1);
            }
        }

        int find(int n) {
            if (n <= a) {
                for (int num : head.keySet()) {
                    n -= head.get(num);
                    if (n <= 0)
                        return num;
                }
            } else if (n <= a + b) {
                n -= a;
                for (int i = 0; i <= 100; i++) {
                    n -= arr[i];
                    if (n <= 0)
                        return i;
                }
            } else {
                n -= a + b;
                for (int num : tail.keySet()) {
                    n -= tail.get(num);
                    if (n <= 0)
                        return num;
                }
            }
            return -1; // never

        }

    }
}
