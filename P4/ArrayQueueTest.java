package P4;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This class implements comprehensive JUnit testing for the ArrayQueue class.
 * It verifies enqueue/dequeue operations, exception handling, wraparound behavior,
 * resizing correctness, and general queue invariants.
 *
 * All exception tests assert the exact exception message using {@code assertEquals("expected", ex.getMessage())}.
 *
 * ©Copyright Cedarville University, its Computer Science faculty,
 * and the author.
 *
 * @author Brandon Aikman
 * @version 1.0
 * File: ArrayTestQueue.java
 * Created: Oct 2025
 * Summary of Modifications: Initial version
 */
public class ArrayQueueTest {

    /**
     * Tests basic enqueue and dequeue functionality including order preservation
     * and correct size and emptiness tracking.
     */
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

    /**
     * Tests that attempting to dequeue from an empty queue throws
     * a QueueEmptyException with the correct message.
     */
    @Test
    public void dequeueEmptyThrows() {
        ArrayQueue<String> q = new ArrayQueue<>();
        QueueEmptyException ex = assertThrows(QueueEmptyException.class, q::dequeue);
        assertEquals("Tried to deque on empty queue.", ex.getMessage());
    }

    /**
     * Tests that calling front() on an empty queue throws a QueueEmptyException
     * with the correct message.
     */
    @Test
    public void frontEmptyThrows() {
        ArrayQueue<Double> q = new ArrayQueue<>();
        QueueEmptyException ex = assertThrows(QueueEmptyException.class, q::front);
        assertEquals("Tried to front on empty queue.", ex.getMessage());
    }

    /**
     * Tests that enqueueing a null element throws an InvalidDataException
     * with the correct message.
     */
    @Test
    public void invalidDataEnqueueThrows_onNull() {
        ArrayQueue<Object> q = new ArrayQueue<>();
        InvalidDataException ex = assertThrows(InvalidDataException.class, () -> q.enqueue(null));
        assertEquals("Tried inserting null element.", ex.getMessage());
    }

    /**
     * Tests circular wraparound behavior when queue indices reach the end
     * of the internal array and continue from the beginning.
     */
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

    /**
     * Tests that the queue correctly resizes and preserves order when full.
     */
    @Test
    public void resizingOnFullAddsElements() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(2);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        assertEquals(3, q.size());
        assertEquals(Integer.valueOf(10), q.dequeue());
        assertEquals(Integer.valueOf(20), q.dequeue());
        assertEquals(Integer.valueOf(30), q.dequeue());
    }

    /**
     * Tests that multiple resizes preserve correct FIFO order.
     */
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

    /**
     * Tests alternating enqueue and dequeue operations for queue consistency.
     */
    @Test
    public void alternatingEnqueueDequeue() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(3);
        for (int i = 0; i < 50; i++) {
            q.enqueue(i);
            assertEquals(Integer.valueOf(i), q.dequeue());
            assertTrue(q.isEmpty());
        }
    }

    /**
     * Tests repeated cycles of enqueueing and dequeuing to validate
     * wraparound correctness and empty-state restoration.
     */
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

            assertEquals(Integer.valueOf(cycle * 3 + 2), q.dequeue());
            assertEquals(Integer.valueOf(cycle * 3 + 3), q.dequeue());
            assertEquals(Integer.valueOf(cycle * 3 + 4), q.dequeue());
            assertTrue(q.isEmpty());
        }

        q.enqueue(999);
        assertEquals(Integer.valueOf(999), q.dequeue());
        assertTrue(q.isEmpty());
    }

    /**
     * Tests that front() does not modify the queue and always returns
     * the current front element correctly.
     */
    @Test
    public void frontDoesNotRemove() throws Exception {
        ArrayQueue<String> q = new ArrayQueue<>();
        q.enqueue("a");
        q.enqueue("b");
        assertEquals("a", q.front());
        assertEquals(2, q.size());
        assertEquals("a", q.front());
        assertEquals(2, q.size());
        assertEquals("a", q.dequeue());
        assertEquals("b", q.front());
    }

    /**
     * Tests that size() and isEmpty() stay consistent across various
     * enqueue and dequeue operations.
     */
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

    /**
     * Tests enqueueing after the queue becomes empty to verify proper
     * resetting of front index and continued correct behavior.
     */
    @Test
    public void enqueueAfterEmptyingResetsBehavior() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(Integer.valueOf(1), q.dequeue());
        assertEquals(Integer.valueOf(2), q.dequeue());
        assertTrue(q.isEmpty());
        q.enqueue(10);
        q.enqueue(11);
        assertEquals(Integer.valueOf(10), q.front());
        assertEquals(Integer.valueOf(10), q.dequeue());
        assertEquals(Integer.valueOf(11), q.dequeue());
        assertTrue(q.isEmpty());
    }

    /**
     * Tests that ArrayQueue supports multiple data types through generics.
     */
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

    /**
     * Tests queue behavior when capacity is 1, ensuring proper reuse
     * of the single slot and correct size reporting.
     */
    @Test
    public void smallCapacityOneBehavesCorrectly() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(1);
        q.enqueue(7);
        assertEquals(1, q.size());
        assertEquals(Integer.valueOf(7), q.dequeue());
        assertTrue(q.isEmpty());
        q.enqueue(8);
        assertEquals(Integer.valueOf(8), q.dequeue());
    }

    /**
     * Tests that complex enqueue/dequeue sequences preserve order.
     */
    @Test
    public void orderPreservedAfterComplexSequence() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        for (int i = 0; i < 4; i++) q.enqueue(i);
        assertEquals(Integer.valueOf(0), q.dequeue());
        assertEquals(Integer.valueOf(1), q.dequeue());
        q.enqueue(4);
        q.enqueue(5);
        assertEquals(Integer.valueOf(2), q.dequeue());
        assertEquals(Integer.valueOf(3), q.dequeue());
        assertEquals(Integer.valueOf(4), q.dequeue());
        assertEquals(Integer.valueOf(5), q.dequeue());
    }

    /**
     * Performs stress testing by interleaving enqueue and dequeue operations
     * repeatedly to ensure stability and correctness.
     */
    @Test
    public void mixedOperationsStressTest() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(5);
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            q.enqueue(i + 1000);
            q.enqueue(i + 2000);
            assertTrue(q.size() >= 3);
            q.dequeue();
            q.dequeue();
        }
        while (!q.isEmpty()) {
            q.dequeue();
        }
        assertTrue(q.isEmpty());
    }

    /**
     * Tests that enqueue(null) throws InvalidDataException with the correct message.
     */
    @Test
    public void dequeueReturnsNullIfNullWasEnqueued_whenAllowed_butImplementationThrows() throws Exception {
        ArrayQueue<Object> q = new ArrayQueue<>();
        InvalidDataException ex = assertThrows(InvalidDataException.class, () -> q.enqueue(null));
        assertEquals("Tried inserting null element.", ex.getMessage());
    }

    /**
     * Tests that consecutive dequeue attempts on an empty queue
     * each throw QueueEmptyException with the correct message.
     */
    @Test
    public void consecutiveDequeuesAfterEmptyThrowEachTime() {
        ArrayQueue<Integer> q = new ArrayQueue<>();
        QueueEmptyException ex1 = assertThrows(QueueEmptyException.class, q::dequeue);
        assertEquals("Tried to deque on empty queue.", ex1.getMessage());
        QueueEmptyException ex2 = assertThrows(QueueEmptyException.class, q::dequeue);
        assertEquals("Tried to deque on empty queue.", ex2.getMessage());
    }

    /**
     * Tests enqueue/dequeue behavior with custom object types,
     * ensuring equals() is respected.
     */
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
     * Tests that enqueue(null) consistently throws InvalidDataException with exact message.
     */
    @Test
    public void enqueueNullThrowsInvalidDataException_withExactMessage() {
        ArrayQueue<String> q = new ArrayQueue<>();
        InvalidDataException ex = assertThrows(InvalidDataException.class, () -> q.enqueue(null));
        assertEquals("Tried inserting null element.", ex.getMessage());
    }

    /**
     * Tests resizing behavior when the queue is wrapped and nonzero front index.
     * Ensures FIFO order is maintained after resizing.
     */
    @Test
    public void resizeDuringWrappedStatePreservesOrder() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        assertEquals(Integer.valueOf(1), q.dequeue());
        assertEquals(Integer.valueOf(2), q.dequeue());
        q.enqueue(5);
        q.enqueue(6);
        for (int i = 7; i <= 20; i++) q.enqueue(i);
        int expected = 3;
        while (!q.isEmpty()) {
            assertEquals(Integer.valueOf(expected), q.dequeue());
            expected++;
        }
        assertEquals(21, expected);
    }

    /**
     * Tests large-scale resizing and order preservation after many expansions.
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
     * Tests that front() consistently returns the current head element
     * even after wraparound and resizing.
     */
    @Test
    public void frontIsStableAcrossOperationsAndResizes() throws Exception {
        ArrayQueue<String> q = new ArrayQueue<>(3);
        q.enqueue("one");
        q.enqueue("two");
        assertEquals("one", q.front());
        q.dequeue();
        assertEquals("two", q.front());
        q.enqueue("three");
        q.enqueue("four");
        q.enqueue("five");
        assertEquals("two", q.front());
        assertEquals("two", q.dequeue());
        assertEquals("three", q.front());
    }

    /**
     * Tests repeated dequeue attempts on an empty queue to confirm
     * consistent QueueEmptyException messages.
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
     * Tests that size() remains accurate after many enqueue and dequeue cycles.
     */
    @Test
    public void sizeReflectsOperationsEvenAfterCycles() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>(4);
        assertEquals(0, q.size());
        q.enqueue(1); q.enqueue(2); q.enqueue(3);
        assertEquals(3, q.size());
        q.dequeue();
        assertEquals(2, q.size());
        for (int i = 0; i < 100; i++) {
            q.enqueue(i + 10);
            q.enqueue(i + 20);
            q.dequeue();
        }
        assertEquals(102, q.size());
    }
}
