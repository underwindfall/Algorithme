package leetcode.datastructure.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    //time O(N)
    //espace O(N)
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

    //time O(n)
    //espace O(n)
    class BFS {
        public Node cloneGraph(Node node) {
            if (node == null) {
                return node;
            }
    
            HashMap<Node, Node> visited = new HashMap<>();
    
            // 将题目给定的节点添加到队列
            LinkedList<Node> queue = new LinkedList<Node> ();
            queue.add(node);
            // 克隆第一个节点并存储到哈希表中
            visited.put(node, new Node(node.val, new ArrayList<>()));
    
            // 广度优先搜索
            while (!queue.isEmpty()) {
                // 取出队列的头节点
                Node n = queue.poll();
                // 遍历该节点的邻居
                for (Node neighbor: n.neighbors) {
                    if (!visited.containsKey(neighbor)) {
                        // 如果没有被访问过，就克隆并存储在哈希表中
                        visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                        // 将邻居节点加入队列中
                        queue.add(neighbor);
                    }
                    // 更新当前节点的邻居列表
                    visited.get(n).neighbors.add(visited.get(neighbor));
                }
            }
    
            return visited.get(node);
        }
    }
}
