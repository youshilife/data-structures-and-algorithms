package life.youshi.队列.顺序队列.循环队列.固定容量;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircularArrayQueueTest {
    private CircularArrayQueue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new CircularArrayQueue<>(5);
        queue.enqueue("C");
        queue.enqueue("Java");
        queue.enqueue("Python");
    }

    @Test
    public void getHead() {
        assertEquals("C", queue.getHead());
        queue.clear();
        assertThrows(RuntimeException.class, () -> queue.getHead());
    }

    @Test
    public void enqueue() {
        queue.enqueue("Go");
        assertEquals("C", queue.getHead());
        assertEquals(4, queue.length());
        queue.enqueue("Rust");
        assertEquals("C", queue.getHead());
        assertTrue(queue.isFull());
        assertThrows(RuntimeException.class, () -> queue.enqueue("JavaScript"));

        queue.clear();
        queue.enqueue("JavaScript");
        assertEquals("JavaScript", queue.getHead());
        assertEquals(1, queue.length());
    }

    @Test
    public void dequeue() {
        assertEquals("C", queue.dequeue());
        assertEquals(2, queue.length());
        assertEquals("Java", queue.dequeue());
        queue.enqueue("Go");
        assertEquals("Python", queue.dequeue());
        assertEquals("Go", queue.dequeue());
        assertEquals(0, queue.length());
        assertThrows(RuntimeException.class, () -> queue.dequeue());
    }

    @Test
    public void traverse() {
        queue.dequeue();
        queue.enqueue("Go");
        queue.enqueue("Rust");
        queue.dequeue();
        queue.enqueue("JavaScript");
        System.out.println("非空队：");
        queue.traverse(element -> System.out.println("- " + element));

        queue.clear();
        System.out.println("空队：");
        queue.traverse(element -> System.out.println("- " + element));
    }
}
