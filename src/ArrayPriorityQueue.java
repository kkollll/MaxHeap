import java.util.Random;

public class ArrayPriorityQueue<E extends Comparable<E>> implements Queue<E>{

    private class Array<E> {
        private E[] data;
        private int size;

        public Array(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        public void add(int index, E e) {

            if (size == data.length) {
                resize(2 * data.length);
            }

            if (index < 0 || index > size) {
                throw new IllegalArgumentException("add failed. Require index >= 0 and index <= size.");
            }

            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = e;
            size++;
        }

        private void resize(int newCapacity) {
            E[] newData = (E[])new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Get failed. Index is illegal.");
            }
            return data[index];
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Remove failed. Index is illegal.");
            }

            E ret = data[index];

            System.arraycopy(data, index + 1, data, index, size - (index + 1));
            size--;
            data[size] = null; // loitering objects != memory leak

            if (size == data.length / 4 && data.length != 0) { //复杂度震荡 Lazy处理
                resize(data.length / 2);
            }
            return ret;
        }

    }

    private Array<E> arr;
    private int size;

    public ArrayPriorityQueue(int capacity) {
        arr = new Array(capacity);
        size = 0;
    }

    public ArrayPriorityQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        enqueue(arr, e, 0);
    }

    private void enqueue(Array arr, E e, int index) {
        for (int i = 0; i < arr.size; i++) {
            if (e.compareTo((E) arr.get(i)) <= 0) {
                arr.add(i, e);
                size++;
                return;
            }
            index++;
        }
        arr.add(index, e);
        size++;
    }


    @Override
    public E dequeue() {
        return arr.removeLast();
    }

    @Override
    public E getFront() {
        return arr.get(0);
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("tail [");
        for (int i = 0; i < arr.size; i++) {
            res.append(arr.get(i));
            if (i != arr.size - 1) {
                res.append(", ");
            }
        }
        res.append("] front");
        return res.toString();
    }

    public static void main(String[] args) {

        Queue<Integer> queue = new ArrayPriorityQueue<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            queue.enqueue(random.nextInt(100));
            System.out.println(queue);

        }
        System.out.println();
        System.out.println(queue.dequeue());
        System.out.println(queue);
    }
}
