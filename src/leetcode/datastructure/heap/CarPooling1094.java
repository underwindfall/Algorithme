package leetcode.datastructure.heap;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

// https://leetcode-cn.com/problems/car-pooling/
public class CarPooling1094 {
    // time O(N*logN)
    // space O(N)
    class MapSolution {
        public boolean carPooling(int[][] trips, int capacity) {
            Map<Integer, Integer> timestamp = new TreeMap<>();
            for (int[] trip : trips) {
                int startPassenger = timestamp.getOrDefault(trip[1], 0) + trip[0];
                timestamp.put(trip[1], startPassenger);

                int endPassenger = timestamp.getOrDefault(trip[2], 0) - trip[0];
                timestamp.put(trip[2], endPassenger);
            }
            int usedCapacity = 0;
            for (int passengerChange : timestamp.values()) {
                usedCapacity += passengerChange;
                if (usedCapacity > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

    // time O(max(n, 1001))
    // space O(1)
    class BucketSort {
        public boolean carPooling(int[][] trips, int capacity) {
            int[] timestamp = new int[1001];
            for (int[] trip : trips) {
                timestamp[trip[1]] += trip[0];
                timestamp[trip[2]] -= trip[0];
            }

            int usedCapacity = 0;
            for (int number : timestamp) {
                usedCapacity += number;
                if (usedCapacity > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

    // time O(n*logn)
    // space O(n)
    class Heap {
        public boolean carPooling(int[][] trips, int capacity) {
            // Sort by start location. Since we can only go towards east and not west,
            // we have to choose the smallest start location first.
            //
            // If two candidates have same start location,
            // we sort by number of passengers, trip with least number of passengers first.
            // this way, we reduce the likely hood of filling the car, if we prioritized the
            // trip with more passengers, we are more likely to go out of capacity!
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

            for (int[] trip : trips) {
                int start = trip[1], end = trip[2], numberOfPassengers = trip[0];

                pq.add(new int[] { start, numberOfPassengers });
                pq.add(new int[] { end, -numberOfPassengers });
            }

            int usedCapacity = 0;
            while (!pq.isEmpty()) {
                usedCapacity += pq.poll()[1];
                if (usedCapacity > capacity) {
                    return false;
                }
            }
            return true;
        }
    }

}
