package life.youshi.栈.顺序栈.固定容量;

import java.util.function.Consumer;

/**
 * 顺序栈
 *
 * <p>容量为实例化时指定的容量，不会自动扩容。</p>
 *
 * @param <E> 数据元素类型
 */
public class ArrayStack<E> {
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
     * 栈长（栈中元素数量）
     */
    private int length;

    /**
     * 初始化（使用默认容量）。
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 初始化。
     *
     * @param capacity 容量
     */
    public ArrayStack(int capacity) {
        setCapacity(capacity);
        // 分配存储空间
        this.elements = new Object[this.capacity];
        // 长度初始为0
        this.length = 0;
    }

    /**
     * 清空栈。
     */
    public void clear() {
        length = 0;
    }

    /**
     * 判断栈是否为空。
     *
     * @return 是/否
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * 判断栈是否已满。
     *
     * @return 是/否
     */
    public boolean isFull() {
        return length == capacity;
    }

    /**
     * 获取栈长。
     *
     * @return 栈长
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
     * 获取栈顶元素值。
     *
     * @return 栈顶元素值
     * @throws RuntimeException 如果栈为空
     */
    public E top() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }

        return (E) elements[length - 1];
    }

    /**
     * 将一个元素值压入栈中，成为新栈顶。
     *
     * @param value 元素值
     */
    public void push(E value) {
        if (isFull()) {
            throw new RuntimeException("栈已满！");
        }

        elements[length] = value;
        length++;
    }

    /**
     * 将栈顶元素出栈。
     *
     * @return 栈顶元素值
     */
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }

        E value = (E) elements[length - 1];
        length--;

        return value;
    }

    /**
     * 从栈底到栈顶依次遍历每一个元素。
     *
     * @param function 遍历过程中处理每一个元素的函数。
     *                 函数接收1个参数：
     *                 1. 当前元素值
     */
    public void traverse(Consumer<E> function) {
        for (int i = 0; i < length; i++) {
            function.accept((E) elements[i]);
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
}
