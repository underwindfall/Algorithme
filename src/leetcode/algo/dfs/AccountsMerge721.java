package leetcode.algo.dfs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode-cn.com/problems/accounts-merge
public class AccountsMerge721 {
    class DFS {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            // email ,-----email set
            Map<String, Set<String>> graph = new HashMap<>();
            // email --- username
            Map<String, String> name = new HashMap<>();
            // build graph

            for (List<String> account : accounts) {
                String userName = account.get(0);
                for (int i = 1; i < account.size(); i++) {
                    if (!graph.containsKey(account.get(i))) {
                        graph.put(account.get(i), new HashSet<>());
                    }
                    name.put(account.get(i), userName);

                    if (i == 1)
                        continue;

                    graph.get(account.get(i)).add(account.get(i - 1));
                    graph.get(account.get(i - 1)).add(account.get(i));
                }
            }

            Set<String> visited = new HashSet<>();
            List<List<String>> res = new LinkedList<>();

            // DFS graph
            for (String email : name.keySet()) {
                List<String> list = new LinkedList<>();
                if (visited.add(email)) {
                    dfs(graph, email, visited, list);
                    Collections.sort(list);
                    list.add(0, name.get(email));
                    res.add(list);
                }
            }

            return res;
        }

        void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
            list.add(email);
            for (String next : graph.get(email)) {
                if (visited.add((next))) {
                    dfs(graph, next, visited, list);
                }
            }
        }
    }

    class BFS {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            // email ,-----email set
            Map<String, Set<String>> graph = new HashMap<>();
            // email --- username
            Map<String, String> name = new HashMap<>();
            // build graph

            for (List<String> account : accounts) {
                String userName = account.get(0);
                for (int i = 1; i < account.size(); i++) {
                    if (!graph.containsKey(account.get(i))) {
                        graph.put(account.get(i), new HashSet<>());
                    }
                    name.put(account.get(i), userName);

                    if (i == 1)
                        continue;

                    graph.get(account.get(i)).add(account.get(i - 1));
                    graph.get(account.get(i - 1)).add(account.get(i));
                }
            }

            Set<String> visited = new HashSet<>();
            List<List<String>> res = new LinkedList<>();

            for (String email : graph.keySet()) {
                if (visited.add(email)) {

                }
            }
            return res;
        }

        void bfs(Map<String, Set<String>> graph, Set<String> visited, String email, List<String> list) {
            Queue<String> q = new LinkedList<>();
            q.add(email);
            while (!q.isEmpty()) {
                String tmp = q.poll();
                list.add(tmp);
                for (String neigboEmail : graph.get(tmp)) {
                    if (!visited.contains(neigboEmail)) {
                        q.add(neigboEmail);
                        visited.add(neigboEmail);
                    }
                }
            }
        }
    }
}
