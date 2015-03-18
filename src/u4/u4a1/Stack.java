package u4.u4a1;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Dynamically growing stack.
 */
public class Stack {
    private int[] buffer;
    private int size;

    /**
     * Creates a new stack
     *
     * @param capacity the initial capacity of the stack
     */
    public Stack(int capacity) {
        buffer = new int[capacity];
        size = 0;
    }

    /**
     * Converts stack to a string representation.
     *
     * @return A ", "-separated list of the numbers, enclosed in square brackets. Bottom numbers come first.
     */
    public String toString() {
        return Arrays.toString(Arrays.copyOf(buffer, size));
    }

    /**
     * Doubles the capacity of the stack.
     * <p>
     * Copies all objects to a new buffer of doubled size.
     */
    private void grow() {
        buffer = Arrays.copyOf(buffer, buffer.length * 2);
    }

    /**
     * Pushes a number onto the top of this stack.
     * <p>
     * Grows the stack if necessary.
     *
     * @param number the number to be pushed onto this stack.
     */
    public void push(int number) {
        if (size == buffer.length) grow();
        buffer[size++] = number;
    }

    /**
     * Removes the number at the top of this stack and returns it as the value of this function.
     *
     * @return The number at the top of this stack
     * @throws java.util.EmptyStackException if this stack is empty
     */
    public int pop() throws EmptyStackException {
        if (empty()) throw new EmptyStackException();
        return buffer[--size];
    }

    /**
     * Looks at the number at the top of this stack without removing it from the stack.
     *
     * @return the number at the top of this stack
     * @throws java.util.EmptyStackException if this stack is empty
     */
    public int peek() throws EmptyStackException {
        if (empty()) throw new EmptyStackException();
        return buffer[size - 1];
    }

    /**
     * Tests if this stack is empty.
     *
     * @return true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Get the size of this stack.
     *
     * @return the current number of items on this stack
     */
    public int size() {
        return size;
    }

    /**
     * Get the capacity of this stack.
     *
     * @return the maximum number of items this stack can hold without having to grow
     */
    public int capacity() {
        return buffer.length;
    }
}
