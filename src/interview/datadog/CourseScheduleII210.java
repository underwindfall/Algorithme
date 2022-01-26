package interview.datadog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://leetcode-cn.com/problems/course-schedule-ii/
public class CourseScheduleII210 {
    // time O(N)
    // espace O(N)
    class BFS {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] incLinkCounts = new int[numCourses];
            List<List<Integer>> adjs = new ArrayList<>(numCourses);
            initGraphs(incLinkCounts, adjs, prerequisites);
            return bfs(incLinkCounts, adjs);
        }

        void initGraphs(int[] inkLinkCounts, List<List<Integer>> adjs, int[][] prerequisites) {
            int n = inkLinkCounts.length;
            while (n-- > 0) {
                adjs.add(new ArrayList<>());
            }
            for (int[] course : prerequisites) {
                inkLinkCounts[course[0]]++;
                adjs.get(course[1]).add(course[0]);
            }
        }

        int[] bfs(int[] incLinkCounts, List<List<Integer>> adjs) {
            int[] result = new int[incLinkCounts.length];
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < incLinkCounts.length; i++) {
                if (incLinkCounts[i] == 0) {
                    queue.add(i);
                }
            }

            int visited = 0;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                result[visited++] = curr;
                for (int adj : adjs.get(curr)) {
                    incLinkCounts[adj]--;
                    if (incLinkCounts[adj] == 0) {
                        queue.add(adj);
                    }
                }
            }

            return visited == incLinkCounts.length ? result : new int[0];
        }
    }

    // time O(M + E)
    // espace O(M + E)
    class DFS {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // prepare
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] pair : prerequisites) {
                int prev = pair[1];
                int next = pair[0];
                graph.get(prev).add(next);
            }

            Map<Integer, Integer> visited = new HashMap<>();
            // initail visited
            for (int i = 0; i < numCourses; i++) {
                visited.put(i, 0);// 0 -> unvisited, 1 -> visiting, 2 -> visited
            }

            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                if (!topoSort(res, graph, visited, i))
                    return new int[0];
            }

            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = res.get(numCourses - i - 1);
            }
            return result;
        }

        // the return value of this function only contains the ifCycle info and does not
        // interfere dfs process. if there is Cycle, then return false
        private boolean topoSort(List<Integer> res, List<List<Integer>> graph, Map<Integer, Integer> visited, int i) {
            int visit = visited.get(i);
            if (visit == 2) {// when visit = 2, which means the subtree whose root is i has been dfs
                             // traversed and all the nodes in subtree has been put in the result(if we
                             // request), so we do not need to traverse it again
                return true;
            }
            if (visit == 1) {
                return false;
            }

            visited.put(i, 1);
            for (int j : graph.get(i)) {
                if (!topoSort(res, graph, visited, j))
                    return false;
            }
            visited.put(i, 2);
            res.add(i);// the only difference with traversing a graph

            return true;
        }

    }
}
