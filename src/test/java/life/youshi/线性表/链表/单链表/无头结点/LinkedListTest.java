package life.youshi.线性表.链表.单链表.无头结点;

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
        assertEquals(node1, list.get(0));
        assertEquals(node2, list.get(1));
        assertEquals(node3, list.get(2));
        assertThrows(RuntimeException.class, () -> list.get(-1));
        assertThrows(RuntimeException.class, () -> list.get(3));
    }

    @Test
    public void get2() {
        assertEquals(node1, list.get("C"));
        assertEquals(node2, list.get("Java"));
        assertEquals(node3, list.get("Python"));
        assertNull(list.get("Go"));
    }

    @Test
    public void getFirst() {
        assertEquals(node1, list.getFirst());
        list.clear();
        assertNull(list.getFirst());
    }

    @Test
    public void getLast() {
        assertEquals(node3, list.getLast());
        list.clear();
        assertNull(list.getLast());
    }

    @Test
    public void previous() {
        assertNull(list.previous(node1));
        assertEquals(node1, list.previous(node2));
        assertEquals(node2, list.previous(node3));

        LinkedList<String>.Node node = list.createNode("Go");
        assertThrows(RuntimeException.class, () -> list.previous(node));
    }

    @Test
    public void next() {
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
        list.insert(null, node);
        assertEquals(4, list.length());
        assertEquals(node, list.getFirst());
        assertEquals(node1, list.next(node));
    }

    @Test
    public void insert2() {
        // 插入到表中
        LinkedList<String>.Node node = list.createNode("Go");
        list.insert(node1, node);
        assertEquals(4, list.length());
        assertEquals(node1, list.getFirst());
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
        assertEquals(node1, list.delete(null));
        assertEquals(2, list.length());
        assertEquals(node2, list.getFirst());

        list.clear();
        assertNull(list.delete(list.getFirst()));
    }

    @Test
    public void delete2() {
        // 删除表中
        assertEquals(node2, list.delete(node1));
        assertEquals(2, list.length());
        assertEquals(node1, list.getFirst());
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
