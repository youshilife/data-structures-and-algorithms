package life.youshi.线性表.链表.单链表.有头结点;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {
    private LinkedList<String> list;
    private LinkedList<String>.Node node1;
    private LinkedList<String>.Node node2;
    private LinkedList<String>.Node node3;

    @Before
    public void setUp() throws Exception {
        list = new LinkedList<>();
        node1 = list.createNode("C");
        node2 = list.createNode("Java");
        node3 = list.createNode("Python");
        list.insert(list.getLast(), node1);
        list.insert(list.getLast(), node2);
        list.insert(list.getLast(), node3);
    }

    @Test
    public void clear() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void isEmpty() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void length() {
        assertEquals(3, list.length());
        list.clear();
        assertEquals(0, list.length());
    }

    @Test
    public void createNode() {
        LinkedList<String>.Node node = list.createNode("Go");
        assertEquals("Go", node.getData());
        assertNull(node.getNext());

        node = list.new Node("Rust", node1);
        assertEquals("Rust", node.getData());
        assertEquals(node1.getData(), node.getNext().getData());
    }

    @Test
    public void get1() {
        assertEquals(list.getHead(), list.get(-1));
        assertEquals(node1, list.get(0));
        assertEquals(node2, list.get(1));
        assertEquals(node3, list.get(2));
        assertThrows(RuntimeException.class, () -> list.get(-2));
        assertThrows(RuntimeException.class, () -> list.get(3));
    }

    @Test
    public void get2() {
        assertEquals(list.getHead(), list.get(null));
        assertEquals(node1, list.get("C"));
        assertEquals(node2, list.get("Java"));
        assertEquals(node3, list.get("Python"));
        assertNull(list.get("Go"));
    }

    @Test
    public void getHead() {
        assertNotNull(list.getHead());
        assertEquals(node1, list.next(list.getHead()));

        list.clear();
        assertNotNull(list.getHead());
        assertNull(list.next(list.getHead()));
    }

    @Test
    public void getLast() {
        assertEquals(node3, list.getLast());

        list.clear();
        assertEquals(list.getHead(), list.getLast());
    }

    @Test
    public void previous() {
        assertNull(list.previous(list.getHead()));
        assertEquals(list.getHead(), list.previous(node1));
        assertEquals(node1, list.previous(node2));
        assertEquals(node2, list.previous(node3));

        LinkedList<String>.Node node = list.createNode("Go");
        assertThrows(RuntimeException.class, () -> list.previous(node));
    }

    @Test
    public void next() {
        assertEquals(node1, list.next(list.getHead()));
        assertEquals(node2, list.next(node1));
        assertEquals(node3, list.next(node2));
        assertNull(list.next(node3));

        LinkedList<String>.Node node = list.createNode("Go");
        assertNull(list.next(node));
    }

    @Test
    public void insert1() {
        // 插入到表头
        LinkedList<String>.Node node = list.createNode("Go");
        assertThrows(RuntimeException.class, () -> list.insert(null, node));
        list.insert(list.getHead(), node);
        assertEquals(4, list.length());
        assertEquals(node, list.next(list.getHead()));
        assertEquals(node1, list.next(node));
    }

    @Test
    public void insert2() {
        // 插入到表中
        LinkedList<String>.Node node = list.createNode("Go");
        list.insert(node1, node);
        assertEquals(4, list.length());
        assertEquals(node1, list.next(list.getHead()));
        assertEquals(node, list.next(node1));
        assertEquals(node2, list.next(node));
    }

    @Test
    public void insert3() {
        // 插入到表尾
        LinkedList<String>.Node node = list.createNode("Go");
        list.insert(list.getLast(), node);
        assertEquals(4, list.length());
        assertEquals(node, list.getLast());
        assertEquals(node, list.next(node3));
        assertNull(list.next(node));
    }

    @Test
    public void delete1() {
        // 删除表头
        assertThrows(RuntimeException.class, () -> list.delete(null));
        assertEquals(node1, list.delete(list.getHead()));
        assertEquals(2, list.length());
        assertEquals(node2, list.next(list.getHead()));

        list.clear();
        assertNull(list.delete(list.getHead()));
    }

    @Test
    public void delete2() {
        // 删除表中
        assertEquals(node2, list.delete(node1));
        assertEquals(2, list.length());
        assertEquals(node1, list.next(list.getHead()));
        assertEquals(node3, list.next(node1));
    }

    @Test
    public void delete3() {
        // 删除表尾
        assertNull(list.delete(node3));
        assertEquals(3, list.length());

        assertEquals(node3, list.delete(node2));
        assertEquals(2, list.length());
        assertEquals(node2, list.getLast());

        list.clear();
        assertNull(list.delete(list.getLast()));
    }

    @Test
    public void traverse() {
        System.out.println("非空表：");
        list.traverse((node) -> System.out.println("- " + node.getData()));
        System.out.println("空表：");
        list.clear();
        list.traverse((node) -> System.out.println("- " + node.getData()));
    }
}
