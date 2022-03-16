package leetcode.algo.lrucache;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/lru-cache/
//https://github.com/JakeWharton/DiskLruCache
// time O(1)
// space O(n)
/**
 * 插入：检查当前键值对是否已经存在于哈希表：
 * 
 * 如果存在，则更新键值对，并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作）
 * 
 * 如果不存在，则检查哈希表容量是否已经达到容量：
 * 
 * 没达到容量：插入哈希表，并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作） 
 * 已达到容量：先从链表尾部找到待删除元素进行删除（delete操作），
 * 然后再插入哈希表，并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作）
 * 
 * 
 * 
 * 查询：如果没在哈希表中找到该 Key，直接返回 ；如果存在该 Key，则将对应的值返回，
 * 并将当前键值对所对应的 Node 节点调整到链表头部（refresh 操作）
 */
public class LRUCache146 {
    class Node {
        int k, v;
        Node l, r;

        Node(int _k, int _v) {
            k = _k;
            v = _v;
        }
    }

    int n;
    Node head, tail;
    Map<Integer, Node> map;

    public LRUCache146(int capacity) {
        n = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.r = tail;
        tail.l = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            refresh(node);
            return node.v;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.v = value;
        } else {
            if (map.size() == n) {
                Node del = tail.l;
                map.remove(del.k);
                delete(del);
            }
            node = new Node(key, value);
            map.put(key, node);
        }
        refresh(node);
    }

    // refresh 操作分两步：
    // 1. 先将当前节点从双向链表中删除（如果该节点本身存在于双向链表中的话）
    // 2. 将当前节点添加到双向链表头部
    void refresh(Node node) {
        delete(node);
        node.r = head.r;
        node.l = head;
        head.r.l = node;
        head.r = node;
    }

    // delete 操作：将当前节点从双向链表中移除
    // 由于我们预先建立 head 和 tail 两位哨兵，因此如果 node.l 不为空，则代表了 node 本身存在于双向链表（不是新节点）
    void delete(Node node) {
        if (node.l != null) {
            Node left = node.l;
            left.r = node.r;
            node.r.l = left;
        }
    }
}
