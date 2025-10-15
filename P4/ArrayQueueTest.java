package P4;

import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Comprehensive JUnit 5 tests for the ArrayQueue implementation.
 * All exception tests assert the exact exception message via assertEquals("expected", ex.getMessage()).
 */
public class ArrayQueueTest {

    @Test
    public void enqueueDequeueBasic() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

        q.enqueue(10);
        assertFalse(q.isEmpty());
        assertEquals(1, q.size());
        assertEquals(Integer.valueOf(10), q.front());

        q.enqueue(20);
        q.enqueue(30);
        assertEquals(3, q.size());
        assertEquals(Integer.valueOf(10), q.dequeue());
        assertEquals(Integer.valueOf(20), q.front());
        assertEquals(2, q.size());
        assertEquals(Integer.valueOf(20), q.dequeue());
        assertEquals(Integer.valueOf(30), q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void dequeueEmptyThrows() {
        ArrayQueue<String> q = new ArrayQueue<>();
        QueueEmptyException ex = assertThrows(QueueEmptyException.class, q::dequeue);
        assertEquals("Tried to deque on empty queue.", ex.getMessage());
    }

    @Test
    public void wrongTypeThrows() {
        // Must use Object so the compiler allows inserting different runtime types.
        ArrayQueue<Object> q = new ArrayQueue<>();
        q.enqueue(5); // Integer
        InvalidDataException ex = assertThrows(InvalidDataException.class, () -> q.enqueue("test")); // String
        assertEquals("Cannot enqueue element of different type.", ex.getMessage());
    }

    @Test
    public void frontEmptyThrows() {
        ArrayQueue<Double> q = new ArrayQueue<>();
        QueueEmptyException ex = assertThrows(QueueEmptyException.class, q::front);
        assertEquals("Tried to front on empty queue.", ex.getMessage());
    }

    @Test
    public void invalidDataEnqueueThrows_onNull() {
        ArrayQueue<Object> q = new ArrayQueue<>();
        InvalidDataException ex = assertThrows(InvalidDataException.class, () -> q.enqueue(null));
        assertEquals("Tried inserting null element.", ex.getMessage());
    }

    @Test
    public void wrapAroundBehavior() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        assertEquals(4, q.size());
        assertEquals(Integer.valueOf(1), q.dequeue());
        assertEquals(Integer.valueOf(2), q.dequeue());
        assertEquals(2, q.size());

        q.enqueue(5);
        q.enqueue(6);
        assertEquals(4, q.size());

        assertEquals(Integer.valueOf(3), q.dequeue());
        assertEquals(Integer.valueOf(4), q.dequeue());
        assertEquals(Integer.valueOf(5), q.dequeue());
        assertEquals(Integer.valueOf(6), q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void resizingOnFullAddsElements() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(2);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30); // triggers resize path (implementation-specific)

        assertEquals(3, q.size());
        assertEquals(Integer.valueOf(10), q.dequeue());
        assertEquals(Integer.valueOf(20), q.dequeue());
        assertEquals(Integer.valueOf(30), q.dequeue());
    }

    /* ------------------ Additional thorough tests ------------------ */

    @Test
    public void multipleResizesMaintainOrder() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(2);
        final int N = 200;
        for (int i = 0; i < N; i++) {
            q.enqueue(i);
        }
        assertEquals(N, q.size());
        for (int i = 0; i < N; i++) {
            assertEquals(Integer.valueOf(i), q.dequeue());
        }
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
    }

    @Test
    public void alternatingEnqueueDequeue() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(3);
        for (int i = 0; i < 50; i++) {
            q.enqueue(i);
            assertEquals(Integer.valueOf(i), q.dequeue());
            assertTrue(q.isEmpty());
        }
    }

    @Test
    public void repeatedWrapAroundCycles_emptyEachCycle() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(3);

        for (int cycle = 0; cycle < 30; cycle++) {
            q.enqueue(cycle * 3 + 1);
            q.enqueue(cycle * 3 + 2);
            q.enqueue(cycle * 3 + 3);
            assertEquals(3, q.size());

            assertEquals(Integer.valueOf(cycle * 3 + 1), q.dequeue());
            q.enqueue(cycle * 3 + 4);

            // drain remaining in order so queue is empty at cycle end
            assertEquals(Integer.valueOf(cycle * 3 + 2), q.dequeue());
            assertEquals(Integer.valueOf(cycle * 3 + 3), q.dequeue());
            assertEquals(Integer.valueOf(cycle * 3 + 4), q.dequeue());

            assertTrue(q.isEmpty());
        }

        // queue is empty now
        q.enqueue(999);
        assertEquals(Integer.valueOf(999), q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void frontDoesNotRemove() throws Exception {
        ArrayQueue<String> q = new ArrayQueue<>();
        q.enqueue("a");
        q.enqueue("b");
        assertEquals("a", q.front());
        assertEquals(2, q.size());
        assertEquals("a", q.front()); // repeated peek
        assertEquals(2, q.size());
        assertEquals("a", q.dequeue());
        assertEquals("b", q.front());
    }

    @Test
    public void sizeAndIsEmptyConsistency() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(5);
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
        q.enqueue(1);
        assertEquals(1, q.size());
        assertFalse(q.isEmpty());
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(3, q.size());
        q.dequeue();
        assertEquals(2, q.size());
        q.dequeue();
        q.dequeue();
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }

    @Test
    public void enqueueAfterEmptyingResetsBehavior() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(Integer.valueOf(1), q.dequeue());
        assertEquals(Integer.valueOf(2), q.dequeue());
        assertTrue(q.isEmpty());
        // Enqueue again after emptying - front index should wrap/behave properly
        q.enqueue(10);
        q.enqueue(11);
        assertEquals(Integer.valueOf(10), q.front());
        assertEquals(Integer.valueOf(10), q.dequeue());
        assertEquals(Integer.valueOf(11), q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void worksWithDifferentTypes() throws Exception {
        ArrayQueue<String> qs = new ArrayQueue<>();
        qs.enqueue("one");
        qs.enqueue("two");
        assertEquals("one", qs.dequeue());
        assertEquals("two", qs.dequeue());

        ArrayQueue<Character> qc = new ArrayQueue<>();
        qc.enqueue('x');
        qc.enqueue('y');
        assertEquals(Character.valueOf('x'), qc.dequeue());
        assertEquals(Character.valueOf('y'), qc.dequeue());
    }

    @Test
    public void smallCapacityOneBehavesCorrectly() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(1);
        q.enqueue(7);
        assertEquals(1, q.size());
        assertEquals(Integer.valueOf(7), q.dequeue());
        assertTrue(q.isEmpty());
        // After dequeue, enqueue again
        q.enqueue(8);
        assertEquals(Integer.valueOf(8), q.dequeue());
    }

    @Test
    public void orderPreservedAfterComplexSequence() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        for (int i = 0; i < 4; i++) q.enqueue(i); // 0,1,2,3
        assertEquals(Integer.valueOf(0), q.dequeue()); // 1,2,3
        assertEquals(Integer.valueOf(1), q.dequeue()); // 2,3
        q.enqueue(4); // 2,3,4
        q.enqueue(5); // 2,3,4,5 -> may trigger resize in implementation
        assertEquals(Integer.valueOf(2), q.dequeue());
        assertEquals(Integer.valueOf(3), q.dequeue());
        assertEquals(Integer.valueOf(4), q.dequeue());
        assertEquals(Integer.valueOf(5), q.dequeue());
    }

    @Test
    public void mixedOperationsStressTest() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(5);
        for (int i = 0; i < 100; i++) {
            // enqueue 3, dequeue 2 pattern repeatedly
            q.enqueue(i);
            q.enqueue(i + 1000);
            q.enqueue(i + 2000);
            assertTrue(q.size() >= 3);
            q.dequeue();
            q.dequeue();
            // queue should still accept more
        }
        // drain remaining safely
        while (!q.isEmpty()) {
            q.dequeue();
        }
        assertTrue(q.isEmpty());
    }

    @Test
    public void dequeueReturnsNullIfNullWasEnqueued_whenAllowed_butImplementationThrows() throws Exception {
        // Implementation throws on null enqueue; test verifies that exact message
        ArrayQueue<Object> q = new ArrayQueue<>();
        InvalidDataException ex = assertThrows(InvalidDataException.class, () -> q.enqueue(null));
        assertEquals("Tried inserting null element.", ex.getMessage());
    }

    @Test
    public void consecutiveDequeuesAfterEmptyThrowEachTime() {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        QueueEmptyException ex1 = assertThrows(QueueEmptyException.class, q::dequeue);
        assertEquals("Tried to deque on empty queue.", ex1.getMessage());

        // still empty - another dequeue should also throw
        QueueEmptyException ex2 = assertThrows(QueueEmptyException.class, q::dequeue);
        assertEquals("Tried to deque on empty queue.", ex2.getMessage());
    }

    @Test
    public void customTypeEnqueueDequeue() throws Exception {
        class Point {
            final int x, y;
            Point(int x, int y) { this.x = x; this.y = y; }
            @Override public boolean equals(Object o) {
                if (!(o instanceof Point)) return false;
                Point p = (Point) o;
                return x == p.x && y == p.y;
            }
        }

        ArrayQueue<Point> q = new ArrayQueue<>();
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        q.enqueue(p1);
        q.enqueue(p2);
        assertEquals(2, q.size());
        assertEquals(p1, q.front());
        assertEquals(p1, q.dequeue());
        assertEquals(p2, q.dequeue());
        assertTrue(q.isEmpty());
    }

    /**
     * Test that enqueue(null) throws InvalidDataException with an exact message.
     */
    @Test
    public void enqueueNullThrowsInvalidDataException_withExactMessage() {
        ArrayQueue<String> q = new ArrayQueue<>();
        InvalidDataException ex = assertThrows(InvalidDataException.class, () -> q.enqueue(null));
        assertEquals("Tried inserting null element.", ex.getMessage());
    }

    /**
     * Force wraparound and then force a resize while elements are wrapped.
     * This ensures resizing preserves element order even when front != 0.
     */
    @Test
    public void resizeDuringWrappedStatePreservesOrder() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4); // small capacity
        // Fill, then remove two to move front forward
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4); // array full
        assertEquals(Integer.valueOf(1), q.dequeue());
        assertEquals(Integer.valueOf(2), q.dequeue());
        // Now front has moved; add items to wrap around
        q.enqueue(5);
        q.enqueue(6); // now buffer contains 3,4,5,6 in logical order
        // Force resizing by adding many items (implementation doubles when full)
        for (int i = 7; i <= 20; i++) q.enqueue(i);
        // Now drain everything and ensure FIFO order from 3..20
        int expected = 3;
        while (!q.isEmpty()) {
            assertEquals(Integer.valueOf(expected), q.dequeue());
            expected++;
        }
        assertEquals(21, expected); // should have consumed through 20
    }

    /**
     * Repeatedly enqueue huge number of items to cause many resizes and ensure order.
     */
    @Test
    public void manyResizesMaintainFIFOOrder() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(2);
        final int N = 1000;
        for (int i = 0; i < N; i++) q.enqueue(i);
        assertEquals(N, q.size());
        for (int i = 0; i < N; i++) {
            assertEquals(Integer.valueOf(i), q.dequeue());
        }
        assertTrue(q.isEmpty());
    }

    /**
     * front() should not modify the queue state and should always reflect the current head.
     * After multiple operations that move front and after resizes, front() must be accurate.
     */
    @Test
    public void frontIsStableAcrossOperationsAndResizes() throws Exception {
        ArrayQueue<String> q = new ArrayQueue<>(3);
        q.enqueue("one");
        q.enqueue("two");
        assertEquals("one", q.front());
        q.dequeue();
        assertEquals("two", q.front());
        // create wrap + resize
        q.enqueue("three");
        q.enqueue("four"); // may wrap
        q.enqueue("five"); // force resize for many implementations
        assertEquals("two", q.front());
        // Dequeue and check front updates correctly
        assertEquals("two", q.dequeue());
        assertEquals("three", q.front());
    }

    /**
     * Ensure that calling dequeue repeatedly on an empty queue repeatedly throws QueueEmptyException.
     */
    @Test
    public void repeatedDequeueOnEmptyAlwaysThrowsQueueEmptyException() {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        for (int i = 0; i < 3; i++) {
            QueueEmptyException ex = assertThrows(QueueEmptyException.class, q::dequeue);
            assertEquals("Tried to deque on empty queue.", ex.getMessage());
        }
    }

    /**
     * Validate that size() always reflects number of enqueued - dequeued elements even after many cycles.
     */
    @Test
    public void sizeReflectsOperationsEvenAfterCycles() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        assertEquals(0, q.size());
        q.enqueue(1); q.enqueue(2); q.enqueue(3);
        assertEquals(3, q.size());
        q.dequeue();
        assertEquals(2, q.size());
        // many cycles
        for (int i = 0; i < 100; i++) {
            q.enqueue(i + 10);
            q.enqueue(i + 20);
            q.dequeue();
        }
        // compute expected size manually: start at 2, each loop +2 -1 => +1 per loop -> 2 + 100 = 102
        assertEquals(102, q.size());
    }
}