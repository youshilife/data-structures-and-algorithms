package life.youshi.队列.顺序队列.循环队列.固定容量;

import java.util.function.Consumer;

/**
 * 循环顺序队列
 *
 * <p>容量为实例化时指定的容量，不会自动扩容。</p>
 *
 * @param <E> 数据元素类型
 */
public class CircularArrayQueue<E> {
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
     *
     * <p>此值为最大元素数量。因为要空一个空间来区分队空和队满，因此数组的容量为{@code capacity + 1}。</p>
     */
    private int capacity;
    /**
     * 队头游标
     *
     * <p>指向队头元素。</p>
     */
    private int front;
    /**
     * 队尾游标
     *
     * <p>指向队尾元素的下一个位置。</p>
     */
    private int rear;

    /**
     * 初始化（使用默认容量）。
     */
    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 初始化。
     *
     * @param capacity 容量
     */
    public CircularArrayQueue(int capacity) {
        setCapacity(capacity);
        // 分配存储空间，多分配一个空间
        this.elements = new Object[this.capacity + 1];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 清空表。
     */
    public void clear() {
        front = 0;
        rear = 0;
    }

    /**
     * 判断表是否为空。
     *
     * @return 是/否
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断表是否已满。
     *
     * @return 是/否
     */
    public boolean isFull() {
        return nextIndex(rear) == front;
    }

    /**
     * 获取长度。
     *
     * @return 长度
     */
    public int length() {
        return (rear + capacity - front) % capacity;
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
     * 获取队头元素值。
     *
     * @return 队头元素值
     * @throws RuntimeException 如果队列为空
     */
    public E getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }

        return (E) elements[front];
    }

    /**
     * 将一个元素值入队，作为新的队尾。
     *
     * @param value 元素值
     * @throws RuntimeException 如果队列已满
     */
    public void enqueue(E value) {
        if (isFull()) {
            throw new RuntimeException("队列已满！");
        }

        elements[rear] = value;
        rear = nextIndex(rear);
    }

    /**
     * 将队头元素出队。
     *
     * @return 队头元素值
     * @throws RuntimeException 如果队列为空
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }

        E value = (E) elements[front];
        front = nextIndex(front);

        return value;
    }

    /**
     * 从队头到队尾依次遍历每一个元素。
     *
     * @param function 遍历过程中处理每一个元素的函数。
     *                 函数接收1个参数：
     *                 1. 当前元素值
     */
    public void traverse(Consumer<E> function) {
        for (int i = front; i != rear; i = nextIndex(i)) {
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

    /**
     * 获取下一个下标。
     *
     * @param index 当前下标
     * @return 下一个下标
     */
    private int nextIndex(int index) {
        return (index + 1) % (capacity + 1);
    }
}
