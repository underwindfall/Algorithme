package leetcode.datastructure.hash;

import java.util.LinkedList;

public class MyHashSet {

    private Bucket[] bucketArray;
    private int keyRange;

    MyHashSet() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < bucketArray.length; i++) {
            this.bucketArray[i] = new Bucket();
        }
    }

    protected int _hash(int key) {
        return (key % this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    public boolean contians(int key) {
        int bucketIndex = this._hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }

    class Bucket {
        private LinkedList<Integer> container;

        public Bucket() {
            container = new LinkedList<Integer>();
        }

        public void insert(Integer key) {
            int index = this.container.indexOf(key);
            if (index == -1) {
                this.container.addFirst(index);
            }
        }

        public void delete(Integer key) {
            this.container.remove(key);
        }

        public boolean exists(Integer key) {
            int index = this.container.indexOf(key);
            return index != -1;
        }
    }
}
