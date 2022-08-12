package leetcode.algo.lrucache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

// https://leetcode.cn/problems/lfu-cache/
public class LFUCache460 {

    // time O(1)
    // https://leetcode.cn/problems/lfu-cache/solution/java-13ms-shuang-100-shuang-xiang-lian-biao-duo-ji/
    class DoubleLinkedList {
        class LFUCache {
            Map<Integer, Node> cache; // 存储缓存的内容
            Map<Integer, DoublyLinkedList> freqMap; // 存储每个频次对应的双向链表
            int size;
            int capacity;
            int min; // 存储当前最小频次

            public LFUCache(int capacity) {
                cache = new HashMap<>(capacity);
                freqMap = new HashMap<>();
                this.capacity = capacity;
            }

            public int get(int key) {
                Node node = cache.get(key);
                if (node == null) {
                    return -1;
                }
                freqInc(node);
                return node.value;
            }

            public void put(int key, int value) {
                if (capacity == 0) {
                    return;
                }
                Node node = cache.get(key);
                if (node != null) {
                    node.value = value;
                    freqInc(node);
                } else {
                    if (size == capacity) {
                        DoublyLinkedList minFreqLinkedList = freqMap.get(min);
                        cache.remove(minFreqLinkedList.tail.pre.key);
                        minFreqLinkedList.removeNode(minFreqLinkedList.tail.pre); // 这里不需要维护min,
                                                                                  // 因为下面add了newNode后min肯定是1.
                        size--;
                    }
                    Node newNode = new Node(key, value);
                    cache.put(key, newNode);
                    DoublyLinkedList linkedList = freqMap.get(1);
                    if (linkedList == null) {
                        linkedList = new DoublyLinkedList();
                        freqMap.put(1, linkedList);
                    }
                    linkedList.addNode(newNode);
                    size++;
                    min = 1;
                }
            }

            void freqInc(Node node) {
                // 从原freq对应的链表里移除, 并更新min
                int freq = node.freq;
                DoublyLinkedList linkedList = freqMap.get(freq);
                linkedList.removeNode(node);
                if (freq == min && linkedList.head.post == linkedList.tail) {
                    min = freq + 1;
                }
                // 加入新freq对应的链表
                node.freq++;
                linkedList = freqMap.get(freq + 1);
                if (linkedList == null) {
                    linkedList = new DoublyLinkedList();
                    freqMap.put(freq + 1, linkedList);
                }
                linkedList.addNode(node);
            }
        }

        class Node {
            int key;
            int value;
            int freq = 1;
            Node pre;
            Node post;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        class DoublyLinkedList {
            Node head;
            Node tail;

            public DoublyLinkedList() {
                head = new Node();
                tail = new Node();
                head.post = tail;
                tail.pre = head;
            }

            void removeNode(Node node) {
                node.pre.post = node.post;
                node.post.pre = node.pre;
            }

            void addNode(Node node) {
                node.post = head.post;
                head.post.pre = node;
                head.post = node;
                node.pre = head;
            }
        }
    }

    // time O(1)
    class LFUCache {
        Map<Integer, Node> cache; // 存储缓存的内容
        Map<Integer, LinkedHashSet<Node>> freqMap; // 存储每个频次对应的双向链表
        int size;
        int capacity;
        int min; // 存储当前最小频次

        public LFUCache(int capacity) {
            cache = new HashMap<>(capacity);
            freqMap = new HashMap<>();
            this.capacity = capacity;
        }

        // get操作的具体逻辑大致是这样：

        // 如果key不存在则返回-1
        // 如果key存在，则返回对应的value，同时:
        // 将元素的访问频率+1
        // 将元素从访问频率i的链表中移除，放到频率i+1的链表中
        // 如果频率i的链表为空，则从频率哈希表中移除这个链表

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            freqInc(node);
            return node.value;
        }
        // put操作就要复杂多了，大致包括下面几种情况

        // 如果key已经存在，修改对应的value，并将访问频率+1
        // 将元素从访问频率i的链表中移除，放到频率i+1的链表中
        // 如果频率i的链表为空，则从频率哈希表中移除这个链表
        // 如果key不存在
        // 缓存超过最大容量，则先删除访问频率最低的元素，再插入新元素
        // 新元素的访问频率为1，如果频率哈希表中不存在对应的链表需要创建
        // 缓存没有超过最大容量，则插入新元素
        // 新元素的访问频率为1，如果频率哈希表中不存在对应的链表需要创建

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                freqInc(node);
            } else {
                if (size == capacity) {
                    Node deadNode = removeNode();
                    cache.remove(deadNode.key);
                    size--;
                }
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode(newNode);
                size++;
            }
        }

        void freqInc(Node node) {
            // 从原freq对应的链表里移除, 并更新min
            int freq = node.freq;
            LinkedHashSet<Node> set = freqMap.get(freq);
            set.remove(node);
            if (freq == min && set.size() == 0) {
                min = freq + 1;
            }
            // 加入新freq对应的链表
            node.freq++;
            LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
            if (newSet == null) {
                newSet = new LinkedHashSet<>();
                freqMap.put(freq + 1, newSet);
            }
            newSet.add(node);
        }

        void addNode(Node node) {
            LinkedHashSet<Node> set = freqMap.get(1);
            if (set == null) {
                set = new LinkedHashSet<>();
                freqMap.put(1, set);
            }
            set.add(node);
            min = 1;
        }

        Node removeNode() {
            LinkedHashSet<Node> set = freqMap.get(min);
            Node deadNode = set.iterator().next();
            set.remove(deadNode);
            return deadNode;
        }
    }

    class Node {
        int key;
        int value;
        int freq = 1;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
