import java.util.Random;

public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MinHeap() {
        data = new Array<>();
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
        if (e.compareTo((E) data.get(parent(i))) < 0) {
            data.set(i, data.get(parent(i)));
            data.set(parent(i), e);
            return siftUp(data, e, parent(i));
        } else {
            return data;
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
        data = siftDown(data, e, 0);
        return ret;
    }

    private Array<E> siftDown(Array<E> data, E e, int i) {

        if (leftChild(i) <= size() - 1 && e.compareTo(data.get(leftChild(i))) > 0) {
            if (rightChild(i) <= size() - 1 && data.get(leftChild(i)).compareTo(data.get(rightChild(i))) > 0) {
                data.set(i, data.get(rightChild(i)));
                data.set(rightChild(i), e);
                data = siftDown(data, e, rightChild(i));
            } else {
                data.set(i, data.get(leftChild(i)));
                data.set(leftChild(i), e);
                data = siftDown(data, e, leftChild(i));
            }
        } else if (rightChild(i) <= size() - 1 && e.compareTo(data.get(rightChild(i))) > 0) {
            data.set(i, data.get(rightChild(i)));
            data.set(rightChild(i), e);
            data = siftDown(data, e, rightChild(i));
        }

        return data;
    }

    public static void main(String[] args) {
        MinHeap<Integer> MinHeap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            MinHeap.add(random.nextInt(100));
        }
        System.out.println(MinHeap.data);
        System.out.println(MinHeap.extractMax());
        System.out.println(MinHeap.data);
    }
}
