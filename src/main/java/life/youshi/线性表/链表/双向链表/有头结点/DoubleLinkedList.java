package life.youshi.线性表.链表.双向链表.有头结点;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 双向链表
 *
 * <p>带头结点，第一个元素结点的直接前驱为头结点。</p>
 *
 * @param <E> 数据元素类型
 */
public class DoubleLinkedList<E> {
    /**
     * 链表结点
     */
    public class Node {
        /**
         * 元素数据
         */
        private E data;
        /**
         * 前驱指针
         */
        private Node previous;
        /**
         * 后继指针
         */
        private Node next;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node previous, Node next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 头指针
     *
     * <p>始终指向头结点。</p>
     */
    private final Node head;

    public DoubleLinkedList() {
        // 创建头结点
        head = createNode(null);
    }

    /**
     * 清空表。
     */
    public void clear() {
        // 将所有结点之间的链接断开
        Node p = head.next;
        while (p != null) {
            Node temp = p.next;
            p.previous = null;
            p.next = null;
            p = temp;
        }
        head.next = null;
    }

    /**
     * 判断表是否为空。
     *
     * @return 是/否
     */
    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 获取表长。
     *
     * @return 表长
     */
    public int length() {
        int length = 0;
        Node p = head.next;
        while (p != null) {
            length++;
            p = p.next;
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
     * @param index 下标（有效范围[-1, length - 1]）
     * @return 结点
     * @throws RuntimeException 如果下标不合法
     */
    public Node get(int index) {
        if (index < -1) {
            throw new RuntimeException("下标不合法！");
        }

        Node p = head;
        int i = -1;
        while (p != null && i < index) {
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
     * @return 结点（表中无该元素值时返回{@code null}；表中有多个该元素值时返回第一个结点；参数为{@code null}时返回头结点）
     */
    public Node get(E value) {
        if (value == null) {
            return head;
        }

        Node p = head.next;
        while (p != null && !Objects.equals(p.data, value)) {
            p = p.next;
        }
        return p;
    }

    /**
     * 获取头结点。
     *
     * @return 头结点
     */
    public Node getHead() {
        return head;
    }

    /**
     * 获取最后一个结点。
     *
     * @return 结点（空表时为头结点）
     */
    public Node getLast() {
        Node p = head;
        while (p != null && p.next != null) {
            p = p.next;
        }
        return p;
    }

    /**
     * 获取指定结点的前驱结点。
     *
     * @param current 当前结点
     * @return 前驱结点（第一个元素结点的前驱结点为头结点；头结点的前驱结点为{@code null}）
     */
    public Node previous(Node current) {
        return current.previous;
    }

    /**
     * 获取指定结点的后继结点。
     *
     * @param current 当前结点
     * @return 后继结点（最后一个元素结点的后继结点为{@code null}）
     */
    public Node next(Node current) {
        return current.next;
    }

    /**
     * 在指定前驱结点之后插入结点。
     *
     * @param previous 前驱结点（不能为{@code null}）
     * @param node 要插入的结点
     */
    public void insert(Node previous, Node node) {
        if (previous == null) {
            throw new RuntimeException("前驱结点不能为null！");
        }

        node.previous = previous;
        node.next = previous.next;
        if (previous.next != null) {
            previous.next.previous = node;
        }
        previous.next = node;
    }

    /**
     * 删除指定前驱结点之后的结点。
     *
     * @param previous 前驱结点（不能为{@code null}）
     * @return 被删除的结点（未删除任何结点时返回{@code null}）
     */
    public Node delete(Node previous) {
        if (previous == null) {
            throw new RuntimeException("前驱结点不能为null！");
        }

        Node node = previous.next;
        if (previous.next != null) {
            previous.next = previous.next.next;
            if (previous.next != null) {
                previous.next.previous = previous;
            }
        }

        // 断开该结点与链表的链接关系
        if (node != null) {
            node.previous = null;
            node.next = null;
        }

        return node;
    }

    /**
     * 从头到尾遍历每一个元素。
     *
     * @param function 遍历过程中处理每一个元素的函数。
     *                 函数接收1个参数：
     *                 1. 当前结点
     */
    public void traverse(Consumer<Node> function) {
        Node p = head.next;
        while (p != null) {
            function.accept(p);
            p = p.next;
        }
    }
}
