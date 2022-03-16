package leetcode.algo.lrucache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/all-oone-data-structure/
// https://www.youtube.com/watch?v=wYqLisoH80w
// 数据类型需要被整理成 1: {leetcode, dfs} -> 2:{bfs} -> 5:{dp}
// 这样从大到小的双向linkedlist形式
public class AllInOne432 {

    // time O(1)
    // space O(n)

    Map<String, Node> map;
    Node head, tail;

    AllInOne432() {
        head = new Node(-1000);
        tail = new Node(-1000);
        head.right = tail;
        tail.left = head;

        map = new HashMap<>();
    }

    void clear(Node node) {
        // 如果当前node的keys是空 以为这它不含任何左右节点关系
        if (node.keys.size() == 0) {
            Node prev = node.left;
            Node next = node.right;
            prev.right = next;
            next.left = prev;
        }
    }


    /*
     * keys -> yes no
     */
    public void inc(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            // 因为要移动到右边了
            node.keys.remove(key);
            int cnt = node.cnt;
            Node next = null;
            if (node.right.cnt == cnt + 1) {
                next = node.right;
            } else {
                // 创建个新的
                next = new Node(cnt + 1);
                next.right = node.right;
                next.left = node;
                node.right.left = next;
                node.right = next;
            }
            next.keys.add(key);
            map.put(key, next);
            clear(node);
        } else {
            Node node = null;
            if (head.right.cnt == 1) {
                node = head.right;
            } else {
                node = new Node(1);
                node.right = head.right;
                node.left = head;
                head.right.left = node;
                head.right = node;
            }
            node.keys.add(key);
            map.put(key, node);
        }
    }


    public void dec(String key) {
        Node node = map.get(key);
        node.keys.remove(key);
        int cnt = node.cnt;
        if (cnt == 1) {
            map.remove(key);
        } else {
            Node prev = null;
            if (node.left.cnt == cnt - 1) {
                prev = node.left;
            } else {
                prev = new Node(cnt - 1);
                prev.right = node;
                prev.left = node.left;
                node.left.right = prev;
                node.left = prev;
            }
            prev.keys.add(key);
            map.put(key, prev);
        }
        clear(node);
    }

    public String getMaxKey() {
        Node node = tail.left;
        for (String str : node.keys)
            return str;
        return "";
    }

    public String getMinKey() {
        Node node = head.right;
        for (String str : node.keys)
            return str;
        return "";
    }


    class Node {
        int cnt;
        // 保留keys
        Set<String> keys = new HashSet<>();
        Node left, right;

        Node(int _cnt) {
            cnt = _cnt;
        }
    }
}
