public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组容量capacity构造Array
     * @param capacity 数组容量
     */
    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     *  默认容量为10
     */
    public Array() {
        this(10);
    }

    /**
     * @return 获取数组种元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * @return 获取数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * @return 判断数组元素是否为0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向数组末尾添加元素
     * @param e
     */
    public void addLast(E e) {

       add(size, e);
    }

    /**
     * 向数组开始添加元素
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在index位置插入元素e
     * @param index 位置
     * @param e 元素
     */
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

    /**
     * 扩容数组
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * 获取索引位置元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    /**
     * 修改索引位置元素
     * @param index
     * @return
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否存在元素e
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回元素下标，找不到返回-1
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除index位置元素
     * @param index
     * @return 删除的元素
     */
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

    /**
     * 删除首元素
     * @return 删除的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除末元素
     * @return 删除的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除特定元素
     * @param e
     * @return
     */
    public boolean removeElement(E e) {
        for (int i = 0; i <= size; i++) {
            if (data[i].equals(e)) {
                remove(i);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }


}
