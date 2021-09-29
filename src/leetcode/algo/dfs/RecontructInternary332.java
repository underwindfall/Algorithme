package leetcode.algo.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

//https://leetcode-cn.com/problems/reconstruct-itinerary
public class RecontructInternary332 {
    // time O(n*logN)
    // space O(n)
    // 想像成树 每次有child 插入 然后postorder便利 逆序数处
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            if (!map.containsKey(from)) {
                map.put(from, new PriorityQueue<>());
            }
            map.get(from).add(to);
        }

        Stack<String> stack = new Stack<>();
        List<String> res = new LinkedList<>();

        stack.add("JFK");

        while (!stack.isEmpty()) {
            while (map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) {
                // lexical order high -> low
                stack.add(map.get(stack.peek()).poll());
            }
            res.add(0, stack.pop());
        }
        return res;
    }
}
