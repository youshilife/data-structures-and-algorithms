package life.youshi.线性表.链表.循环链表.无头结点;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircularLinkedListTest {
    // 三个元素的链表
    private CircularLinkedList<String> list1;
    private CircularLinkedList<String>.Node node11;
    private CircularLinkedList<String>.Node node12;
    private CircularLinkedList<String>.Node node13;
    // 一个元素的链表
    private CircularLinkedList<String> list2;
    private CircularLinkedList<String>.Node node21;
    // 空链表
    private CircularLinkedList<String> list3;

    @Before
    public void setUp() throws Exception {
        list1 = new CircularLinkedList<>();
        node11 = list1.createNode("C");
        node12 = list1.createNode("Java");
        node13 = list1.createNode("Python");
        list1.insert(list1.getLast(), node11);
        list1.insert(list1.getLast(), node12);
        list1.insert(list1.getLast(), node13);

        list2 = new CircularLinkedList<>();
        node21 = list2.createNode("Apple");
        list2.insert(list2.getLast(), node21);

        list3 = new CircularLinkedList<>();
    }

    @Test
    public void clear() {
        assertFalse(list1.isEmpty());
        list1.clear();
        assertTrue(list1.isEmpty());
        assertNull(list1.getFirst());
        assertNull(list1.getLast());
        assertNull(list1.getLast());
        assertTrue(list3.isEmpty());
    }

    @Test
    public void isEmpty() {
        assertFalse(list1.isEmpty());
        assertFalse(list2.isEmpty());
        assertTrue(list3.isEmpty());
        list1.clear();
        assertTrue(list1.isEmpty());
    }

    @Test
    public void length() {
        assertEquals(3, list1.length());
        assertEquals(1, list2.length());
        assertEquals(0, list3.length());
    }

    @Test
    public void createNode() {
        CircularLinkedList<String>.Node node = list1.createNode("Go");
        assertEquals("Go", node.getData());
        assertNull(node.getNext());

        node = list1.new Node("Rust", node11);
        assertEquals("Rust", node.getData());
        assertEquals(node11.getData(), node.getNext().getData());
    }

    @Test
    public void get1() {
        assertEquals(node11, list1.get(0));
        assertEquals(node12, list1.get(1));
        assertEquals(node13, list1.get(2));
        assertThrows(RuntimeException.class, () -> list1.get(-1));
        assertThrows(RuntimeException.class, () -> list1.get(3));
        assertEquals(node21, list2.get(0));
        assertThrows(RuntimeException.class, () -> list2.get(1));
        assertThrows(RuntimeException.class, () -> list3.get(0));
    }

    @Test
    public void get2() {
        assertEquals(node11, list1.get("C"));
        assertEquals(node12, list1.get("Java"));
        assertEquals(node13, list1.get("Python"));
        assertNull(list1.get("Go"));
        assertEquals(node21, list2.get("Apple"));
        assertNull(list3.get("Anything"));
    }

    @Test
    public void getFirst() {
        assertEquals(node11, list1.getFirst());
        assertEquals(node21, list2.getFirst());
        assertNull(list3.getFirst());
    }

    @Test
    public void getLast() {
        assertEquals(node13, list1.getLast());
        assertEquals(node21, list2.getLast());
        assertNull(list3.getLast());
    }

    @Test
    public void previous() {
        assertEquals(node13, list1.previous(node11));
        assertEquals(node11, list1.previous(node12));
        assertEquals(node12, list1.previous(node13));

        assertEquals(node21, list2.previous(node21));
    }

    @Test
    public void next() {
        assertEquals(node12, list1.next(node11));
        assertEquals(node13, list1.next(node12));
        assertEquals(node11, list1.next(node13));

        assertEquals(node21, list2.next(node21));
    }

    @Test
    public void insert1() {
        // 插入到表头
        CircularLinkedList<String>.Node node = list1.createNode("Go");
        list1.insert(null, node);
        assertEquals(4, list1.length());
        assertEquals(node, list1.getFirst());
        assertEquals(node11, list1.next(node));

        node = list2.createNode("Banana");
        list2.insert(null, node);
        assertEquals(2, list2.length());
        assertEquals(node, list2.getFirst());
        assertEquals(node21, list2.next(node));
        assertEquals(node, list2.next(node21));

        node = list3.createNode("Something");
        list3.insert(null, node);
        assertEquals(1, list3.length());
        assertEquals(node, list3.getFirst());
        assertEquals(node, list3.getLast());
        assertEquals(node, list3.next(node));
    }

    @Test
    public void insert2() {
        // 插入到表中
        CircularLinkedList<String>.Node node = list1.createNode("Go");
        list1.insert(node11, node);
        assertEquals(4, list1.length());
        assertEquals(node11, list1.getFirst());
        assertEquals(node, list1.next(node11));
        assertEquals(node12, list1.next(node));
    }

    @Test
    public void insert3() {
        // 插入到表尾
        CircularLinkedList<String>.Node node = list1.createNode("Go");
        list1.insert(list1.getLast(), node);
        assertEquals(4, list1.length());
        assertEquals(node, list1.getLast());
        assertEquals(node, list1.next(node13));
        assertEquals(node11, list1.next(node));

        node = list2.createNode("Banana");
        list2.insert(list2.getLast(), node);
        assertEquals(2, list2.length());
        assertEquals(node, list2.getLast());
        assertEquals(node, list2.next(node21));
        assertEquals(node21, list2.next(node));
    }

    @Test
    public void delete1() {
        // 删除表头
        assertThrows(RuntimeException.class, () -> list1.delete(null));
        assertEquals(node11, list1.delete(list1.getLast()));
        assertEquals(2, list1.length());
        assertEquals(node12, list1.getFirst());

        assertEquals(node21, list2.delete(list2.getLast()));
        assertTrue(list2.isEmpty());
    }

    @Test
    public void delete2() {
        // 删除表中
        assertEquals(node12, list1.delete(node11));
        assertEquals(2, list1.length());
        assertEquals(node11, list1.getFirst());
        assertEquals(node13, list1.next(node11));
    }

    @Test
    public void delete3() {
        // 删除表尾
        assertEquals(node13, list1.delete(node12));
        assertEquals(2, list1.length());
        assertEquals(node12, list1.getLast());

        assertEquals(node21, list2.delete(list2.previous(list2.getLast())));
        assertTrue(list2.isEmpty());
    }

    @Test
    public void traverse() {
        System.out.println("list1从头遍历：");
        list1.traverse(null, node -> System.out.println("- " + node.getData()));
        System.out.println("list1从第二个结点遍历：");
        list1.traverse(node12, node -> System.out.println("- " + node.getData()));
        System.out.println("list2从头遍历：");
        list2.traverse(null, node -> System.out.println("- " + node.getData()));
        System.out.println("list3从头遍历：");
        list3.traverse(null, node -> System.out.println("- " + node.getData()));
    }
}
