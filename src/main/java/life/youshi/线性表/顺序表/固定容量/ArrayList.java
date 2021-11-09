package life.youshi.线性表.顺序表.固定容量;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * 顺序表
 *
 * <p>容量为实例化时指定的容量，不会自动扩容。</p>
 *
 * @param <E> 数据元素类型
 */
public class ArrayList<E> {
    /**
     * 默认容量
     */
    public static final int DEFAULT_CAPACITY = 10;

    /**
     * 数据元素数组
     *
     * <p>因为无法直接创建泛型数组，因此用{@code Object[]}作为数组类型。需要使用原始元素类型时要做强制类型转换。</p>
     */
    private final Object[] elements;
    /**
     * 容量
     */
    private int capacity;
    /**
     * 线性表长度（元素数量）
     */
    private int length;

    /**
     * 初始化（使用默认容量）。
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 初始化。
     *
     * @param capacity 容量
     */
    public ArrayList(int capacity) {
        setCapacity(capacity);
        // 分配存储空间
        this.elements = new Object[this.capacity];
        // 长度初始为0
        this.length = 0;
    }

    /**
     * 清空表。
     */
    public void clear() {
        length = 0;
    }

    /**
     * 判断表是否为空。
     *
     * @return 是/否
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * 判断表是否已满。
     *
     * @return 是/否
     */
    public boolean isFull() {
        return length == capacity;
    }

    /**
     * 获取表长。
     *
     * @return 表长
     */
    public int length() {
        return length;
    }

    /**
     * 获取容量。
     *
     * @return 容量
     */
    public int capacity() {
        return capacity;
    }

    /**
     * 根据下标获取元素值。
     *
     * @param index 下标
     * @return 元素值
     */
    public E get(int index) {
        checkIndex(index, 0, length - 1);
        return (E) elements[index];
    }

    /**
     * 获取指定元素值的下标。
     *
     * @param value 元素值
     * @return 下标（表中不存在该元素值时返回-1；表中存在多个该元素值时返回最小的下标）
     */
    public int indexOf(E value) {
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (Objects.equals(elements[i], value)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 获取指定元素值的直接前驱元素值。
     *
     * @param current 当前元素值
     * @return 直接前驱元素值（第一个元素的直接前驱为{@code null}）
     * @throws RuntimeException 如果当前元素值不在表中
     */
    public E previous(E current) {
        int index = indexOf(current);
        if (index < 0) {
            throw new RuntimeException("指定元素值不在表中！");
        }

        return index > 0 ? (E) elements[index - 1] : null;
    }

    /**
     * 获取指定元素值的直接后继元素值。
     *
     * @param current 当前元素值
     * @return 直接后继元素值（最后一个元素的直接后继为{@code null}）
     * @throws RuntimeException 如果当前元素值不在表中
     */
    public E next(E current) {
        int index = indexOf(current);
        if (index < 0) {
            throw new RuntimeException("指定元素值不在表中！");
        }

        return index < length - 1 ? (E) elements[index + 1] : null;
    }

    /**
     * 插入元素值到指定下标处。
     *
     * @param index 下标
     * @param value 元素值
     * @throws RuntimeException 如果下标不在[0, length]范围内，如果存储空间已满
     */
    public void insert(int index, E value) {
        checkIndex(index, 0, length);
        // 检查容量
        if (isFull()) {
            throw new RuntimeException("表的存储空间已满！");
        }

        // 向后搬移元素
        for (int i = length - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        // 放入元素
        elements[index] = value;
        // 更新表长
        length++;
    }

    /**
     * 删除指定下标处的元素。
     *
     * @param index 下标
     * @return 被删除的元素的值
     * @throws RuntimeException 如果下标不在[0, length-1]范围内
     */
    public E delete(int index) {
        checkIndex(index, 0, length - 1);

        // 取出元素
        E value = (E) elements[index];
        // 向前搬移元素
        for (int i = index + 1; i < length; i++) {
            elements[i - 1] = elements[i];
        }
        // 更新表长
        length--;

        return value;
    }

    /**
     * 从头到尾依次遍历每一个元素。
     *
     * @param function 遍历过程中处理每一个元素的函数。
     *                 函数接收两个参数：
     *                 1. 当前元素值
     *                 2. 当前元素下标
     */
    public void traverse(BiConsumer<E, Integer> function) {
        for (int i = 0; i < length; i++) {
            function.accept((E) elements[i], i);
        }
    }

    /**
     * 设置容量。
     *
     * @param capacity 容量
     */
    private void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("容量不能小于0！");
        }
        this.capacity = capacity;
    }

    /**
     * 检查下标。
     *
     * @param index 下标
     * @param min 最小有效值
     * @param max 最大有效值
     * @throws RuntimeException 如果下标不在[min, max]范围内
     */
    private void checkIndex(int index, int min, int max) {
        if (! (index >= min && index <= max)) {
            throw new RuntimeException("下标不合法！");
        }
    }
}
