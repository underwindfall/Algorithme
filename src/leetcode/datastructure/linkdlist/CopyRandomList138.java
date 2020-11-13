package leetcode.datastructure.linkdlist;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/copy-list-with-random-pointer/
/**
 * 深度拷贝 deep copy
 * 
 * 是指的是在不破坏原有数据的基础上 赋值copy变量
 * 
 * 换而言之就是把变量复制到Heap 上 通过new 这个操作
 */
public class CopyRandomList138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class MapSolution {

        // 思路
        // 1. 把所有的点放进map中
        // 2. 重新从头开始把所有的next点 放进p.next
        // 3. 重新从头开始把所有的random点 放进p.random
        // 4. 返回头节点
        // time O(N)
        // espace O(N)
        public Node copyRandomList(Node head) {
            Map<Node, Node> map = new HashMap<>();
            if (head == null) {
                return null;
            }
            Node p = head;
            while (p != null) {
                map.put(p, new Node(p.val));
                p = p.next;
            }
            p = head;
            while (p != null) {
                map.get(p).next = map.get(p.next);
                map.get(p).random = map.get(p.random);
                p = p.next;
            }
            return map.get(head);
        }
    }

    class Iteractive {
        //time O(n)
        //espace O(1)
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            Node p = head;
            // 首先复制每个node 让其后面都有个复制的变量
            while (p != null) {
                p.next = new Node(p.val);
                p = p.next.next;
            }

            // 给每个复制的node 添加random节点
            p = head;
            while (p != null) {
                if (p.random != null) {
                    p.next.random = p.random;
                }
                p = p.next.next;
            }

            //断开复制的节点
            p = head;
            Node cloneNode = p.next;
            Node clonedStart = cloneNode;
            while (cloneNode.next != null) {
                p.next = p.next.next;
                p = p.next;


                cloneNode.next = cloneNode.next.next;
                cloneNode = cloneNode.next;
            }
            p.next = null;
            return clonedStart;
        }
    }
}
