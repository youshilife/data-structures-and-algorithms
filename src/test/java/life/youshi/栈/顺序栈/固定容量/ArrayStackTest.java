package life.youshi.栈.顺序栈.固定容量;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStackTest {
    private ArrayStack<String> stack;

    @Before
    public void setUp() throws Exception {
        stack = new ArrayStack<>(5);
        stack.push("C");
        stack.push("Java");
        stack.push("Python");
    }

    @Test
    public void top() {
        assertEquals("Python", stack.top());
        stack.clear();
        assertThrows(RuntimeException.class, () -> stack.top());
    }

    @Test
    public void push() {
        stack.push("Go");
        assertEquals("Go", stack.top());
        assertEquals(4, stack.length());
        stack.push("Rust");
        assertEquals("Rust", stack.top());
        assertThrows(RuntimeException.class, () -> stack.push("JavaScript"));

        stack.clear();
        stack.push("JavaScript");
        assertEquals("JavaScript", stack.top());
        assertEquals(1, stack.length());
    }

    @Test
    public void pop() {
        assertEquals("Python", stack.pop());
        assertEquals(2, stack.length());
        assertEquals("Java", stack.pop());
        assertEquals("C", stack.pop());
        assertEquals(0, stack.length());
        assertThrows(RuntimeException.class, () -> stack.pop());
    }
}
