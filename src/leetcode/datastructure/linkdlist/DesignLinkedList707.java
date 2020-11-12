package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/design-linked-list/
public class DesignLinkedList707 {
    class MyLinkedList {

        class Node {
            int val;
            Node next;

            public Node() {

            }

            public Node(int val) {
                this.val = val;
                this.next = null;
            }

            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        Node head;
        int size;

        /** Initialize your data structure here. */
        public MyLinkedList() {
            head = new Node();
            size = 0;
        }

        /**
         * Get the value of the index-th node in the linked list. If the index is
         * invalid, return -1.
         */
        public int get(int index) {
            if (index >= size || index < 0) {
                return -1;
            }

            Node curr = head.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.val;
        }

        /**
         * Add a node of value val before the first element of the linked list. After
         * the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index
         * equals to the length of linked list, the node will be appended to the end of
         * linked list. If index is greater than the length, the node will not be
         * inserted.
         */
        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }

            Node prev = head;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node toAdd = new Node(val);
            toAdd.next = prev.next;
            prev.next = toAdd;

            size++;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }

            Node prev = head;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
            size--;
        }
    }

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList(); int param_1 = obj.get(index);
     * obj.addAtHead(val); obj.addAtTail(val); obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */

    class MyDeLinkedList {
        class Node {
                int val;
                Node next;
                Node prev;
    
                public Node() {
    
                }
    
                public Node(int val) {
                    this.val = val;
                    this.next = null;
                    this.prev = null;
                }
    
                public Node(int val, Node next, Node prev) {
                    this.val = val;
                    this.next = next;
                    this.prev = prev;
                }
            }
    
            Node head;
            Node tail;
            int size;
    
            /** Initialize your data structure here. */
            public MyDeLinkedList() {
                head = new Node();
                tail = new Node();
                head.next = tail;
                tail.prev = head;
                size = 0;
            }
    
            /**
             * Get the value of the index-th node in the linked list. If the index is
             * invalid, return -1.
             */
            public int get(int index) {
                if (index < 0 || index >= size) {
                    return -1;
                }
                Node curr;
                // from head
                if (index < (size - 1) / 2) {
                    curr = head;
                    for (int i = 0; i < index + 1; i++) {
                        curr = curr.next;
                    }
                } else {
                    curr = tail;
                    for (int i = 0; i < size - index; i++) {
                        curr = curr.prev;
                    }
                }
                return curr.val;
            }
    
            /**
             * Add a node of value val before the first element of the linked list. After
             * the insertion, the new node will be the first node of the linked list.
             */
            public void addAtHead(int val) {
                addAtIndex(0, val);
            }
    
            /** Append a node of value val to the last element of the linked list. */
            public void addAtTail(int val) {
                addAtIndex(size, val);
            }
    
            /**
             * Add a node of value val before the index-th node in the linked list. If index
             * equals to the length of linked list, the node will be appended to the end of
             * linked list. If index is greater than the length, the node will not be
             * inserted.
             */
            public void addAtIndex(int index, int val) {
                if (index > size) {
                    return;
                }
                if (index < 0) {
                    index = 0;
                }
    
                Node prev, succ;
                if (index < size / 2) {
                    prev = head;
                    for (int i = 0; i < index; i++) {
                        prev = prev.next;
                    }
                    succ = prev.next;
                } else {
                    succ = tail;
                    for (int i = 0; i < size - index; i++) {
                        succ = succ.prev;
                    }
                    prev = succ.prev;
                }
                Node toAdd = new Node(val);
                toAdd.prev = prev;
                toAdd.next = succ;
                prev.next = toAdd;
                succ.prev = toAdd;
                ++size;
            }
    
            /** Delete the index-th node in the linked list, if the index is valid. */
            public void deleteAtIndex(int index) {
                if (index < 0 || index >= size) {
                    return;
                }
    
                Node prev, succ;
                if (index < size / 2) {
                    prev = head;
                    for (int i = 0; i < index; i++) {
                        prev = prev.next;
                    }
                    succ = prev.next.next;
                } else {
                    succ = tail;
                    for (int i = 0; i < size - index - 1; i++) {
                        succ = succ.prev;
                    }
                    prev = succ.prev.prev;
                }
                prev.next = succ;
                succ.prev = prev;
                --size;
            }
        }
}
