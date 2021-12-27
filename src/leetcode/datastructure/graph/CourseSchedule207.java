package leetcode.datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/course-schedule/
public class CourseSchedule207 {
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
