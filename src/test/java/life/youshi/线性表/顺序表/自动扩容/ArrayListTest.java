package life.youshi.线性表.顺序表.自动扩容;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {
    private ArrayList<String> list;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>(5);
        list.insert(list.length(), "C");
        list.insert(list.length(), "Java");
        list.insert(list.length(), "Python");
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
    public void capacity() {
        list = new ArrayList<>();
        assertEquals(ArrayList.DEFAULT_INITIAL_CAPACITY, list.capacity());
        list = new ArrayList<>(5);
        assertEquals(5, list.capacity());
        list = new ArrayList<>(0);
        assertEquals(0, list.capacity());
    }

    @Test
    public void get() {
        assertEquals("C", list.get(0));
        assertEquals("Java", list.get(1));
        assertEquals("Python", list.get(2));
        assertThrows(RuntimeException.class, () -> list.get(-1));
        assertThrows(RuntimeException.class, () -> list.get(3));
    }

    @Test
    public void indexOf() {
        assertEquals(0, list.indexOf("C"));
        assertEquals(1, list.indexOf("Java"));
        assertEquals(2, list.indexOf("Python"));
        assertEquals(-1, list.indexOf("Go"));
    }

    @Test
    public void previous() {
        assertNull(list.previous("C"));
        assertEquals("C", list.previous("Java"));
        assertEquals("Java", list.previous("Python"));
        assertThrows(RuntimeException.class, () -> list.previous("Go"));
    }

    @Test
    public void next() {
        assertEquals("Java", list.next("C"));
        assertEquals("Python", list.next("Java"));
        assertNull(list.next("Python"));
        assertThrows(RuntimeException.class, () -> list.next("Go"));
    }

    @Test
    public void insert1() {
        // 插入到表头
        list.insert(0, "Go");
        assertEquals(4, list.length());
        assertEquals("Go", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    public void insert2() {
        // 插入到表中
        list.insert(1, "Go");
        assertEquals(4, list.length());
        assertEquals("Go", list.get(1));
        assertEquals("Java", list.get(2));
    }

    @Test
    public void insert3() {
        // 插入到表尾
        list.insert(3, "Go");
        assertEquals(4, list.length());
        assertEquals("Go", list.get(3));
        assertEquals("Python", list.get(2));
    }

    @Test
    public void insert4() {
        // 非法插入位置
        assertThrows(RuntimeException.class, () -> list.insert(4, "Go"));
    }

    @Test
    public void insert5() {
        // 扩容
        list.insert(list.length(), "Go");
        list.insert(list.length(), "Rust");
        assertEquals(5, list.length());
        assertEquals(5, list.capacity());
        list.insert(list.length(), "JavaScript");
        assertEquals(6, list.length());
        assertEquals(5 + ArrayList.CAPACITY_INCREMENT, list.capacity());
        assertEquals("C", list.get(0));
        assertEquals("Rust", list.get(4));
        assertEquals("JavaScript", list.get(5));
    }

    @Test
    public void delete1() {
        // 删除表头
        assertEquals("C", list.delete(0));
        assertEquals(2, list.length());
        assertEquals("Java", list.get(0));
    }

    @Test
    public void delete2() {
        // 删除表中
        assertEquals("Java", list.delete(1));
        assertEquals(2, list.length());
        assertEquals("Python", list.get(1));
    }

    @Test
    public void delete3() {
        // 删除表尾
        assertEquals("Python", list.delete(2));
        assertEquals(2, list.length());
        assertThrows(RuntimeException.class, () -> list.get(2));
    }

    @Test
    public void delete4() {
        // 非法删除位置
        assertThrows(RuntimeException.class, () -> list.delete(3));
    }

    @Test
    public void traverse() {
        System.out.println("非空表：");
        list.traverse((element, index) -> System.out.printf("[%d] %s\n", index, element));
        System.out.println("空表：");
        list.clear();
        list.traverse((element, index) -> System.out.printf("[%d] %s\n", index, element));
    }
}
