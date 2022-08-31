package interview.booking;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
// bookings:[

//     {user = 1, res_id = 1001, checkin = 100, checkout=101 },
//     {user = 2, res_id = 1002, checkin = 104, checkout=105 },
//     {user = 1, res_id = 1003, checkin = 101, checkout=103 },
//     {user = 3, res_id = 1004, checkin = 104, checkout=105},
//     {user = 3, res_id = 1005, checkin = 105, checkout=107},
// 	{user = 4, res_id = 1006, checkin = 106, checkout= 108},
// 	{user = 4, res_id = 1007, checkin = 108, checkout= 110},
// 	{user = 4, res_id = 1008, checkin = 108, checkout= 109},
// 	{user = 4, res_id = 1009, checkin = 110, checkout= 112},
// 	{user = 4, res_id = 1010, checkin = 109, checkout= 113},
// ];
// For user 1 we have the following chain: 100-101-103 which means the user
// had to make two reservations, 1001 and 1003. The task is to retrieve all the bookings per user.
// { 1: {{1001, 1003}}, 3: {{1004, 1005}}, 4 : {{1006, 1007, 1009}, {1006, 1008, 1009}} }
public class GroupReservationByUser {
    
    public Map<Integer, List<List<Integer>>> findBookings(List<Booking> bookings) {
        Map<Integer, List<List<Integer>>> res = new HashMap<>();
        if (bookings == null || bookings.size() == 0) {
            return res;
        }
        Map<Integer, TreeMap<Integer, List<Pair>>> map = new HashMap<>();
        buildGraph(bookings, map);
        for (int userId : map.keySet()) {
            TreeMap<Integer, List<Pair>> graph = map.get(userId);
            dfs(graph, graph.firstKey(), userId, new ArrayList<>(), res);
        }
        return res;
    }

    void dfs(TreeMap<Integer, List<Pair>> graph, int checkIn, int userId, List<Integer> curr,
            Map<Integer, List<List<Integer>>> res) {
        if (!graph.containsKey(checkIn)) {
            if (curr.size() > 2) {
                res.putIfAbsent(userId, new ArrayList<>());
                res.get(userId).add(new ArrayList<>(curr));
            }
            return;
        }
        for (Pair pair : graph.get(checkIn)) {
            curr.add(pair.resId);
            dfs(graph, pair.checkOut, userId, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    // userId -> checkIn -> res_Id + checkOut
    void buildGraph(List<Booking> bookings, Map<Integer, TreeMap<Integer, List<Pair>>> map) {
        for (Booking booking : bookings) {
            if (!map.containsKey(booking.user_id)) {
                map.put(booking.user_id, new TreeMap<>());
            }
            map.get(booking.user_id).putIfAbsent(booking.checkin, new ArrayList<>());
            map.get(booking.user_id).get(booking.checkin).add(new Pair(booking.res_id, booking.checkout));
        }
    }

    class Booking {
        int user_id;
        int res_id;
        int checkin;
        int checkout;

        Booking(int user_id, int res_id, int checkin, int checkout) {
            this.user_id = user_id;
            this.res_id = res_id;
            this.checkin = checkin;
            this.checkout = checkout;
        }
    }

    class Pair {
        int resId;
        int checkOut;

        Pair(int resId, int checkOut) {
            this.resId = resId;
            this.checkOut = checkOut;
        }
    }
}
