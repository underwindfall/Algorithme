package interview.datadog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://leetcode-cn.com/problems/course-schedule/
public class CourseSchedule207 {
    // time O(N+E)
    // espace O(N+E)
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            int[] courses = new int[numCourses];
            for (int i = 0; i< numCourses; i++) {
                graph.putIfAbsent(i, new ArrayList<>());
            }
            for (int[] p : prerequisites) {
                graph.get(p[1]).add(p[0]);
                courses[p[0]]++;
            }

            /*
            Queue<Integer> q = new LinkedList<>();
            
            for (int i = 0; i < courses.length; i++) {
                if (courses[i] == 0) {
                    q.offer(i);
                }
            }
            
            
            
            int learnCourses = 0;
            while (!q.isEmpty()) {
                int c = q.poll();
                learnCourses++;
                for (int adj : graph.get(c)) {
                    courses[adj]--;
                    if (courses[adj] == 0) {
                        q.offer(adj);
                    }
                }
            }
            return learnCourses == numCourses;
            */
            
            int[] vis = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (!dfs(graph, vis, i)) {
                    return false;
                }
            }
            return true;
            
        }
        
        boolean dfs(Map<Integer, List<Integer>> graph, int[] vis, int startCourse) {
            if (vis[startCourse] == 1) {
                return false;
            }
            if (vis[startCourse] == -1) {
                return true;
            }
            vis[startCourse] = 1;
            for (int i : graph.get(startCourse)) {
                if (!dfs(graph, vis, i)) {
                    return false;
                }
            }
            vis[startCourse] = -1;
            return true;
        }
    }
    // bfs
    // time O(N+E)
    // espace O(N+E)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] courses = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<>(numCourses);
        initGraphs(courses, prerequisites, adjs);
        return bfs(courses, adjs);
    }

    void initGraphs(int[] courses, int[][] prerequisites, List<List<Integer>> adjs) {
        int n = courses.length;
        for (int i = 0; i < n; i++) {
            adjs.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[0]]++;
            adjs.get(prerequisite[1]).add(prerequisite[0]);
        }
    }

    boolean bfs(int[] courses, List<List<Integer>> adjs) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == 0) {
                queue.add(i);
            }
        }
        int learnCourses = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            learnCourses++;
            for (int adj : adjs.get(cur)) {
                courses[adj]--;
                if (courses[adj] == 0) {
                    queue.add(adj);
                }
            }
        }
        return learnCourses == courses.length;
    }

    // time O(N+M)
    // space O(N+M)
    class DFS {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adjacency = new ArrayList<>();
            for (int i = 0; i < numCourses; i++)
                adjacency.add(new ArrayList<>());
            int[] flags = new int[numCourses];
            for (int[] cp : prerequisites)
                adjacency.get(cp[1]).add(cp[0]);
            for (int i = 0; i < numCourses; i++)
                if (!dfs(adjacency, flags, i))
                    return false;
            return true;
        }

        private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
            if (flags[i] == 1)
                return false;
            if (flags[i] == -1)
                return true;
            flags[i] = 1;
            for (Integer j : adjacency.get(i))
                if (!dfs(adjacency, flags, j))
                    return false;
            flags[i] = -1;
            return true;
        }

    }
}
