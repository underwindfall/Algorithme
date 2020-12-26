package leetcode.datastructure.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// https://leetcode-cn.com/problems/clone-graph/
public class CloneGraph133 {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class DFS {
        public Node cloneGraph(Node node) {
            Map<Node, Node> visited = new HashMap<>();
            return dfs(visited, node);
        }

        private Node dfs(Map<Node, Node> visited, Node node) {
            if (node == null) {
                return null;
            }
            // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
            if (visited.containsKey(node)) {
                return visited.get(node);
            }

            // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
            Node cloneNode = new Node(node.val);
            // 哈希表存储
            visited.put(node, cloneNode);

            // 遍历该节点的邻居并更新克隆节点的邻居列表
            for (Node neighbor : node.neighbors) {
                cloneNode.neighbors.add(dfs(visited, neighbor));
            }
            return cloneNode;
        }

    }

    class BFS {
        public Node cloneGraph(Node node) {
            return bfs(node);
        }

        private Node bfs(Node node) {
            if (node == null) {
                return node;
            }
            Map<Node, Node> visited = new HashMap<>();
            Queue<Node> queue = new LinkedList<>();
            Node cloneNode = new Node(node.val);
            queue.add(node);
            visited.put(node, cloneNode);
            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                List<Node> neighbors = curr.neighbors;
                for (Node neighbor : neighbors) {
                    if (neighbor != null && !visited.containsKey(neighbor)) {
                        queue.offer(neighbor);
                        visited.put(neighbor, new Node(neighbor.val));
                    }
                    visited.get(curr).neighbors.add(visited.get(neighbor));
                }
            }
            return visited.get(node);
        }
    }
}
