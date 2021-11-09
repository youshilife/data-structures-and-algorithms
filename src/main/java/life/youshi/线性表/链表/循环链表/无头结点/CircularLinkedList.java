package life.youshi.线性表.链表.循环链表.无头结点;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 循环链表
 *
 * <p>不带头结点。第一个元素结点的直接前驱为最后一个元素结点，最后一个元素结点的直接后继为第一个元素结点。</p>
 *
 * @param <E> 数据元素类型
 */
public class CircularLinkedList<E> {
    /**
     * 链表结点
     */
    public class Node {
        /**
         * 元素数据
         */
        private E data;
        /**
         * 后继指针
         */
        private Node next;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 尾指针
     *
     * <p>指向最后一个元素结点，通过{@code tail.next}可获取第一个元素结点。</p>
     *
     * <p>在空表中为{@code null}。</p>
     */
    private Node tail;

    public CircularLinkedList() {
        tail = null;
    }

    /**
     * 清空表。
     */
    public void clear() {
        // 将所有结点之间的链接断开
        if (tail != null) {
            Node p = tail.next;
            tail.next = null;
            while (p != null) {
                Node temp = p.next;
                p.next = null;
                p = temp;
            }
            tail = null;
        }
    }

    /**
     * 判断表是否为空。
     *
     * @return 是/否
     */
    public boolean isEmpty() {
        return tail == null;
    }

    /**
     * 获取表长。
     *
     * @return 表长
     */
    public int length() {
        int length = 0;
        if (tail != null) {
            length = 1;
            Node p = tail.next;
            while (p != tail) {
                length++;
                p = p.next;
            }
        }
        return length;
    }

    /**
     * 创建一个结点。
     *
     * @param value 元素值
     * @return 结点
     */
    public Node createNode(E value) {
        return new Node(value);
    }

    /**
     * 根据下标获取元素结点。
     *
     * @param index 下标
     * @return 结点
     * @throws RuntimeException 如果下标不合法
     */
    public Node get(int index) {
        // 空表中无合法下标
        if (index < 0 || tail == null) {
            throw new RuntimeException("下标不合法！");
        }

        // 令p初始指向第一个结点
        Node p = tail.next;
        int i = 0;
        while (i < index) {
            // 若p已到达最后一个结点，则未找到
            if (p == tail) {
                p = null;
                break;
            }
            p = p.next;
            i++;
        }

        if (p == null) {
            throw new RuntimeException("下标不合法！");
        }

        return p;
    }

    /**
     * 根据元素值获取元素结点。
     *
     * @param value 元素值
     * @return 结点（表中无该元素值时返回{@code null}；表中有多个该元素值时返回第一个结点）
     */
    public Node get(E value) {
        Node p = null;
        if (tail != null) {
            // 令p初始指向第一个结点
            p = tail.next;
            while (!Objects.equals(p.data, value)) {
                // 若p已到达最后一个结点，则未找到
                if (p == tail) {
                    p = null;
                    break;
                }
                p = p.next;
            }
        }

        return p;
    }

    /**
     * 获取第一个元素结点。
     *
     * @return 结点
     */
    public Node getFirst() {
        return tail != null ? tail.next : null;
    }

    /**
     * 获取最后一个元素结点。
     *
     * @return 结点
     */
    public Node getLast() {
        return tail;
    }

    /**
     * 获取指定结点的前驱结点。
     *
     * @param current 当前结点
     * @return 前驱结点（第一个元素结点的前驱结点为最后一个元素结点）
     */
    public Node previous(Node current) {
        Node p = current;
        while (p.next != current) {
            p = p.next;
        }

        return p;
    }

    /**
     * 获取指定结点的后继结点。
     *
     * @param current 当前结点
     * @return 后继结点（最后一个元素结点的后继结点为第一个元素结点）
     */
    public Node next(Node current) {
        return current.next;
    }

    /**
     * 在指定前驱结点之后插入结点。
     *
     * @param previous 前驱结点（{@code null}表示要插入到表头）
     * @param node 要插入的结点
     */
    public void insert(Node previous, Node node) {
        // 插入到表头
        if (previous == null) {
            // 空表
            if (tail == null) {
                tail = node;
                tail.next = tail;
            }
            // 非空表
            else {
                node.next = tail.next;
                tail.next = node;
            }
        } else {
            node.next = previous.next;
            previous.next = node;
            if (previous == tail) {
                tail = node;
            }
        }
    }

    /**
     * 删除指定前驱结点之后的结点。
     *
     * @param previous 前驱结点（不能为{@code null}）
     * @return 被删除的结点（未删除任何结点时返回{@code null}）
     * @throws RuntimeException 如果前驱结点为空
     */
    public Node delete(Node previous) {
        if (previous == null) {
            throw new RuntimeException("前驱结点不能为null！");
        }

        Node node = previous.next;
        // 如果只有一个结点
        if (node.next == node) {
            tail = null;
        } else {
            previous.next = previous.next.next;
            if (node == tail) {
                tail = previous;
            }
        }

        // 断开该结点与链表的链接关系
        node.next = null;

        return node;
    }

    /**
     * 从指定结点开始循环遍历每一个元素。
     *
     * @param current 当前结点（起始结点，为{@code null}表示从头遍历）
     * @param function 遍历过程中处理每一个元素的函数。
     *                 函数接收1个参数：
     *                 1. 当前结点
     */
    public void traverse(Node current, Consumer<Node> function) {
        if (tail != null) {
            Node begin = current != null ? current : tail.next;
            Node p = begin;
            while (true) {
                function.accept(p);
                p = p.next;
                // 如果又回到了起始点，则终止
                if (p == begin) {
                    break;
                }
            }
        }
    }
}
