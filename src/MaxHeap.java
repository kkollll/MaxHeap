import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * Heapify 将一个数组变成堆
     *
     * @param arr
     */
    public MaxHeap(E[] arr) {

        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            data = shiftDown(data, arr[i], i, size());
        }
    }

    public E get(int index) {
        return data.get(index);
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父节点的索引
     *
     * @param index 索引
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index - 0 donsen't has parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 左孩子
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 右孩子
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素 sift up
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        data = siftUp(data, e, size() - 1);
    }

    private Array siftUp(Array data, E e, int i) {
        if (i == 0) {
            return data;
        }
        if (e.compareTo((E) data.get(parent(i))) > 0) {
            data.set(i, data.get(parent(i)));
            data.set(parent(i), e);
            return siftUp(data, e, parent(i));
        } else {
            return data;
        }
    }

    public void sortData() {
        sortData(data, size() - 1);
    }

    /**
     * 堆排序 这里如果也用递归会造成栈溢出
     *
     * @param data
     * @param heapIndex
     * @return
     */
    private void sortData(Array<E> data, int heapIndex) {
        while (heapIndex > 0) {
            // swap 将最大值和最后一个元素交换
            E e = data.getFirst();
            data.set(0, data.get(heapIndex));
            data.set(heapIndex, e);
            data = shiftDown(data, data.getFirst(), 0, heapIndex);
            heapIndex--;
        }
    }

    /**
     * 取出队中最大元素
     *
     * @return
     */
    public E extractMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap is Empty.");
        }
        E ret = data.getFirst();
        data.set(0, data.get(size() - 1));
        E e = data.getFirst();
        data.remove(size() - 1);
        data = shiftDown(data, e, 0, size());
        return ret;
    }

    private Array<E> shiftDown(Array<E> data, E e, int i, int k) {


//        while (leftChild(i) < k) {
//            int j = leftChild(i);
//            if (j + 1 < k && data.get(j).compareTo(data.get(j + 1)) < 0) {
//                j = rightChild(i);
//            }
//            if (e.compareTo(data.get(j)) >= 0) {
//                break;
//            }
//            data.set(i, data.get(j));
//            data.set(j, e);
//            i = j;
//        }

        if (leftChild(i) < k && e.compareTo(data.get(leftChild(i))) < 0) {
            if (rightChild(i) < k && data.get(leftChild(i)).compareTo(data.get(rightChild(i))) < 0) {
                data.set(i, data.get(rightChild(i)));
                data.set(rightChild(i), e);
                data = shiftDown(data, e, rightChild(i), k);
            } else {
                data.set(i, data.get(leftChild(i)));
                data.set(leftChild(i), e);
                data = shiftDown(data, e, leftChild(i), k);
            }
        } else if (rightChild(i) < k && e.compareTo(data.get(rightChild(i))) < 0) {
            data.set(i, data.get(rightChild(i)));
            data.set(rightChild(i), e);
            data = shiftDown(data, e, rightChild(i), k);
        }

        return data;
    }

    /**
     * 取出堆中最大元素，替换成元素e
     *
     * @return
     */
    public E replace(E e) {
        E ret = data.getFirst();
        data.set(0, e);
        data = shiftDown(data, e, 0, size());
        return ret;
    }

    public static void main(String[] args) {
        int n = 10000000;
        Random random = new Random();
        MaxHeap<Integer> maxHeap = new MaxHeap(n);
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
            ;
        }
        maxHeap.sortData();
        for (int i = 1; i < n; i++) {
            if (maxHeap.data.get(i - 1) > maxHeap.data.get(i)) {
                throw new IllegalArgumentException("Error.");
            }
        }
        System.out.println("Sort Complete.");
    }
}