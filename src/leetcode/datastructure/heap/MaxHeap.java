package leetcode.datastructure.heap;

//https://leetcode-cn.com/leetbook/read/heap/evmih5/
public class MaxHeap {
    // 最大堆的实现

    // 使用数组创建完全二叉树的结构，然后使用二叉树构建一个「堆」
    int[] maxHeap;
    // heapSize用于数组的大小，因为数组在创建的时候至少需要指明数组的元素个数
    int heapSize;
    // realSize用于记录「堆」的元素个数
    int realSize = 0;

    public MaxHeap(int heapSize) {
        this.heapSize = heapSize;
        maxHeap = new int[heapSize + 1];
        // 为了便于完全二叉树的管理，数组的第0个索引的元素我们不会使用，请随便设置设置成任何一个值。
        maxHeap[0] = 0;
    }

    // 添加元素函数
    public void add(int element) {
        realSize++;
        // 如果 堆 中元素的个数大于一开始设定的数组的个数， 则返回「Add too many elements」
        if (realSize > heapSize) {
            System.out.println("Add too many elements!");
            realSize--;
            return;
        }
        // 将添加的元素添加到数组中
        maxHeap[realSize] = element;
        // 新增元素的索引位置
        int index = realSize;
        // 新增元素的父节点的索引位置
        // 注意，如果用数组表示完全二叉树，并且根结点存储在数组的索引1的位置的时候，任何一个节点的父节点索引位置为「该节点的索引位置/2」，
        // 任何一个节点的左孩子节点的索引位置为「该节点的索引位置*2」，任何一个节点的右孩子节点的索引位置为「该节点的索引位置*2+1」
        int parent = index / 2;
        // 当添加的元素大于父节点时，需要将父节点的值和新增元素的值交换
        while (maxHeap[index] > maxHeap[parent] && index > 1) {
            int temp = maxHeap[index];
            maxHeap[index] = maxHeap[parent];
            maxHeap[parent] = temp;
            index = parent;
            parent = index / 2;
        }
    }

    // 获取堆顶元素
    public int peek() {
        return maxHeap[1];
    }

    // 删除堆顶元素函数
    public int pop() {
        // 如果当前【堆】的元素个数为0， 则返回「Don't have any element」
        if (realSize < 1) {
            System.out.println("Don't have any element!");
            return Integer.MIN_VALUE;
        } else {
            // 当前【堆】中含有元素
            // realSize >= 1
            int removeElement = maxHeap[1];
            // 将「堆」中的最后一个元素赋值给堆顶元素
            maxHeap[1] = maxHeap[realSize];
            realSize--;
            int index = 1;
            // 当删除的元素不是孩子节点时
            while (index < realSize && index <= realSize / 2) {
                // 被删除节点的左孩子节点
                int left = index * 2;
                // 被删除节点的右孩子节点
                int right = (index * 2) + 1;
                // 当删除节点的元素小于 左孩子节点或者右孩子节点，代表该元素的值小，此时需要将该元素与左、右孩子节点中最大的值进行交换
                if (maxHeap[index] < maxHeap[left] || maxHeap[index] < maxHeap[right]) {
                    if (maxHeap[left] > maxHeap[right]) {
                        int temp = maxHeap[left];
                        maxHeap[left] = maxHeap[index];
                        maxHeap[index] = temp;
                        index = left;
                    } else {
                        int temp = maxHeap[right];
                        maxHeap[right] = maxHeap[index];
                        maxHeap[index] = temp;
                        index = right;
                    }
                } else {
                    break;
                }
            }
            return removeElement;
        }
    }

    // 返回「堆」的元素个数
    public int size() {
        return realSize;
    }

    @Override
    public String toString() {
        if (realSize == 0) {
            return "No element";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 1; i <= realSize; i++) {
                sb.append(maxHeap[i]);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        // 测试用例
        MaxHeap maxheap = new MaxHeap(5);
        maxheap.add(1);
        maxheap.add(2);
        maxheap.add(3);
        // [3,1,2]
        System.out.println(maxheap.toString());
        // 3
        System.out.println(maxheap.peek());
        // 3
        System.out.println(maxheap.pop());
        System.out.println(maxheap.pop());
        System.out.println(maxheap.pop());
        // No element
        System.out.println(maxheap.toString());
        maxheap.add(4);
        // Add too mant elements
        maxheap.add(5);
        // [4,1,2]
        System.out.println(maxheap.toString());
    }
}
