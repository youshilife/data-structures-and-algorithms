package life.youshi.线性表.链表.单链表.无头结点;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 单链表
 *
 * <p>不带头结点，第一个元素结点的直接前驱为{@code null}。</p>
 *
 * @param <E> 数据元素类型
 */
public class LinkedList<E> {
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
     * 头指针
     *
     * <p>指向第一个元素结点。在空表中为{@code null}。</p>
     */
    private Node head;

    public LinkedList() {
    }

    /**
     * 清空表。
     */
    public void clear() {
        // 将所有结点之间的链接断开
        Node p = head;
        while (p != null) {
            Node temp = p.next;
            p.next = null;
            p = temp;
        }
        head = null;
    }

    /**
     * 判断表是否为空。
     *
     * @return 是/否
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 获取表长。
     *
     * @return 表长
     */
    public int length() {
        int length = 0;
        Node p = head;
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
     * @param index 下标
     * @return 结点
     * @throws RuntimeException 如果下标不合法
     */
    public Node get(int index) {
        if (index < 0) {
            throw new RuntimeException("下标不合法！");
        }

        Node p = head;
        int i = 0;
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
     * @return 结点（表中无该元素值时为{@code null}；表中有多个该元素值时为第一个结点）
     */
    public Node get(E value) {
        Node p = head;
        while (p != null && !Objects.equals(p.data, value)) {
            p = p.next;
        }
        return p;
    }

    /**
     * 获取第一个元素结点。
     *
     * @return 结点
     */
    public Node getFirst() {
        return head;
    }

    /**
     * 获取最后一个元素结点。
     *
     * @return 结点
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
     * @return 前驱结点（第一个元素结点的前驱结点为{@code null}）
     * @throws RuntimeException 如果current不在当前链表中
     */
    public Node previous(Node current) {
        // 若是第一个结点，则前驱为null
        if (current == head) {
            return null;
        }

        Node p = head;
        while (p != null && p.next != current) {
            p = p.next;
        }

        // 若出循环时p为null，则表示currentNode不在此链表中
        if (p == null) {
            throw new RuntimeException("该结点不在此链表中！");
        }

        return p;
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
     * @param previous 前驱结点（{@code null}表示要在表头插入）
     * @param node 要插入的结点
     */
    public void insert(Node previous, Node node) {
        // 插入到表头
        if (previous == null) {
            node.next = head;
            head = node;
        } else {
            node.next = previous.next;
            previous.next = node;
        }
    }

    /**
     * 删除指定前驱结点之后的结点。
     *
     * @param previous 前驱结点（{@code null}表示要删除第一个结点）
     * @return 被删除的结点（未删除任何结点时返回{@code null}）
     */
    public Node delete(Node previous) {
        Node node;
        // 删除表头
        if (previous == null) {
            node = head;
            if (head != null) {
                head = head.next;
            }
        } else {
            node = previous.next;
            if (previous.next != null) {
                previous.next = previous.next.next;
            }
        }

        // 断开该结点与链表的链接关系
        if (node != null) {
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
        Node p = head;
        while (p != null) {
            function.accept(p);
            p = p.next;
        }
    }
}
