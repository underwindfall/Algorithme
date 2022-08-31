package interview.booking;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/discuss/interview-question/1580539/Booking.com-or-Stage%3A-OA-(Virtual)-or-Top-k-count-of-hotels
public class TopKCountHotel {
    // Given a list of hotelId, parentHotelId and a score retrieve the top k root
    // parentHotelIds with highest scores:

    // [{0, 1, 10}, {1, 2, 20}, {3, 4, 10}, {7, 8, 5}] K = 2

    // Result: [[2, 30], [4,10]]
    public int[][] topKDFS(int[][] hotels, int k, int n) {
        // map of child_hotel=(parent_hotel, rating)
        Map<Integer, Pair<Integer, Integer>> parent = new HashMap<>();
        for (int[] hotel : hotels) {
            parent.put(hotel[0], new Pair<Integer, Integer>(hotel[1], hotel[2]));
        }
        Map<Integer, Integer> total = new HashMap<>();
        for (int[] hotel : hotels) {
            dfs(hotel[0], 0, parent, total);
        }
        List<int[]> ans = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (total.get(a) - total.get(b)));
        for (Map.Entry<Integer, Integer> entry : total.entrySet()) {
            pq.offer(entry.getKey());
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            int hotel = pq.poll();
            ans.add(new int[] { hotel, total.get(hotel) });
        }
        return ans.toArray(new int[ans.size()][]);
    }

    void dfs(int hotel, int rating, Map<Integer, Pair<Integer, Integer>> parent, Map<Integer, Integer> total) {
        if (!parent.containsKey(hotel)) {
            total.put(hotel, total.getOrDefault(hotel, 0) + rating);
            return;
        }
        // parent rating
        Pair<Integer, Integer> pair = parent.get(hotel);
        dfs(pair.getKey(), rating + pair.getValue(), parent, total);
        // parent.put(hotel, new Pair<>(pair.getKey(), 0));
    }

    class Pair<A, B> {
        A a;
        B b;

        Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        A getKey() {
            return a;
        }

        B getValue() {
            return b;
        }
    }
}
