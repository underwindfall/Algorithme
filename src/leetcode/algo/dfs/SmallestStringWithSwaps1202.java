package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/smallest-string-with-swaps/
public class SmallestStringWithSwaps1202 {
    class UnionFind {
        private int[] parent;

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            if (s == null || s.length() == 0) {
                return null;
            }
            parent = new int[s.length()];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            for (List<Integer> pair : pairs) {
                union(pair.get(0), pair.get(1));
            }

            Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
            char[] sChar = s.toCharArray();
            for (int i = 0; i < sChar.length; i++) {
                int root = find(i);
                map.putIfAbsent(root, new PriorityQueue<>());
                map.get(root).offer(sChar[i]);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sChar.length; i++) {
                sb.append(map.get(find(i)).poll());
            }
            return sb.toString();
        }

        private int find(int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }
            return index;
        }

        private void union(int a, int b) {
            int aParent = find(a);
            int bParent = find(b);
            if (aParent < bParent) {
                parent[bParent] = aParent;
            } else {
                parent[aParent] = bParent;
            }
        }
    }

    @SuppressWarnings("unchecked")
    class DFS {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            char[] arr = s.toCharArray();
            boolean[] visited = new boolean[s.length()];
            List<Integer>[] graph = new List[s.length()];
            for (int i = 0; i < arr.length; i++) {
                graph[i] = new ArrayList<>();
            }

            for (List<Integer> pair : pairs) {
                graph[pair.get(0)].add(pair.get(1));
                graph[pair.get(1)].add(pair.get(0));
            }
            for (int i = 0; i < arr.length; i++) {
                if (!visited[i]) {
                    List<Integer> indexes = new ArrayList<>();
                    List<Character> contents = new ArrayList<>();
                    dfs(graph, arr, indexes, contents, i, visited);
                    Collections.sort(indexes);
                    Collections.sort(contents);
                    for (int j = 0; j < indexes.size(); j++) {
                        arr[indexes.get(j)] = contents.get(j);
                    }
                }
            }
            return new String(arr);
        }


        void dfs(List<Integer>[] graph, char[] arr,
                List<Integer> indexes, List<Character> contents, int start, boolean[] visited) {
            visited[start] = true;
            indexes.add(start);
            contents.add(arr[start]);
            for (int child : graph[start]) {
                if (!visited[child]) {
                    dfs(graph, arr, indexes, contents, start, visited);
                }
            }
        }

    }
}
