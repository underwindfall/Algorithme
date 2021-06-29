package leetcode.datastructure.heap;

//https://leetcode-cn.com/leetbook/read/heap/evmih5/
public class MinHeap {
    // 使用数组创建完全二叉树的结构，然后使用二叉树构建一个「堆」
    int[] minHeap;
    // heapSize用于数组的大小，因为数组在创建的时候至少需要指明数组的元素个数
    int heapSize;
    // realSize用于记录「堆」的元素个数
    int realSize = 0;

    public MinHeap(int heapSize) {
        this.heapSize = heapSize;
        minHeap = new int[heapSize + 1];
        // 为了便于完全二叉树的管理，数组的第0个索引的元素我们不会使用，请随便设置设置成任何一个值。
        minHeap[0] = 0;
    }

    // 添加元素函数
    public void add(int element) {
        realSize++;
        // 如果「堆」中元素的个数大于一开始设定的数组的个数，则返回「Add too many elements」
        if (realSize > heapSize) {
            System.out.println("Add too many elements!");
            realSize--;
            return;
        }
        // 将添加的元素添加到数组中
        minHeap[realSize] = element;
        // 新增元素的索引位置
        int index = realSize;
        // 新增元素的父节点的索引位置
        // 注意，如果用数组表示完全二叉树，并且根结点存储在数组的索引1的位置的时候，任何一个节点的父节点索引位置为「该节点的索引位置/2」，任何一个节点的左孩子节点的索引位置为「该节点的索引位置*2」，任何一个节点的右孩子节点的索引位置为「该节点的索引位置*2+1」
        int parent = index / 2;
        // 当添加的元素小于父节点时，需要将父节点的值和新增元素的值交换
        while (minHeap[index] < minHeap[parent] && index > 1) {
            int temp = minHeap[index];
            minHeap[index] = minHeap[parent];
            minHeap[parent] = temp;
            index = parent;
            parent = index / 2;
        }
    }

    // 获取堆顶元素函数
    public int peek() {
        return minHeap[1];
    }

    // 删除堆顶元素函数
    public int pop() {
        // 如果当前「堆」的元素个数为0， 则返回「Don't have any element」
        if (realSize < 1) {
            System.out.println("Don't have any element!");
            return Integer.MAX_VALUE;
        } else {
            // 当前「堆」中含有元素
            // realSize >= 1
            int removeElement = minHeap[1];
            // 将「堆」中的最后一个元素赋值给堆顶元素
            minHeap[1] = minHeap[realSize];
            realSize--;
            int index = 1;
            // 当删除的元素不是孩子节点时
            while (index < realSize && index <= realSize / 2) {
                // 被删除节点的左孩子节点
                int left = index * 2;
                // 被删除节点的右孩子节点
                int right = (index * 2) + 1;
                // 当删除节点的元素大于 左孩子节点或者右孩子节点，代表该元素的值大，此时需要将该元素与左、右孩子节点中最小的值进行交换
                if (minHeap[index] > minHeap[left] || minHeap[index] > minHeap[right]) {
                    if (minHeap[left] < minHeap[right]) {
                        int temp = minHeap[left];
                        minHeap[left] = minHeap[index];
                        minHeap[index] = temp;
                        index = left;
                    } else {
                        // maxHeap[left] >= maxHeap[right]
                        int temp = minHeap[right];
                        minHeap[right] = minHeap[index];
                        minHeap[index] = temp;
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

    public String toString() {
        if (realSize == 0) {
            return "No element!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 1; i <= realSize; i++) {
                sb.append(minHeap[i]);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(']');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        // 测试用例
        MinHeap minHeap = new MinHeap(3);
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(2);
        // [1,3,2]
        System.out.println(minHeap.toString());
        // 1
        System.out.println(minHeap.peek());
        // 1
        System.out.println(minHeap.pop());
        // [2, 3]
        System.out.println(minHeap.toString());
        minHeap.add(4);
        // Add too mant elements
        minHeap.add(5);
        // [2,3,4]
        System.out.println(minHeap.toString());
    }

}
