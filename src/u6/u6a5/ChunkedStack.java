package u6.u6a5;

import u6.u6a2.IStack;

import java.util.EmptyStackException;

public class ChunkedStack implements IStack {
    /**
     * Linked list of chunks
     */
    private ChunkList chunks;

    /**
     * the number of slots of the last chunk which are used.
     */
    private int used;

    private int chunkcount = 1;

    public String toString() {
        if (empty()) return "[]";

        // First Chunk, may not be full
        StringBuffer buf = new StringBuffer("[");
        for (int i = used; i > 0; i--)
            buf.append(chunks.buffer[i - 1]).append(", ");

        // Following full Chunks
        for (ChunkList iter = chunks.next; iter != null; iter = iter.next) {
            for (int i = ChunkList.chunkSize; i > 0; i--) {
                buf.append(iter.buffer[i - 1]).append(", ");
            }
        }

        buf.delete(buf.length() - 2, buf.length());
        buf.append("]");
        return buf.toString();
    }

    public ChunkedStack() {
        chunks = new ChunkList();
        used = 0;
    }

    public boolean empty() {
        return chunkcount == 1 && used == 0;
    }

    public int peek() throws EmptyStackException {
        if (used == 0) {
            throw new EmptyStackException();
        }

        return chunks.buffer[used - 1];
    }

    public int pop() throws EmptyStackException {
        if (used == 0) {
            throw new EmptyStackException();
        }

        int num = chunks.buffer[--used];
        if (used == 0 && chunks.next != null) {
            chunks = chunks.removeChunk();
            used = ChunkList.chunkSize;
            chunkcount--;
        }

        return num;
    }

    public void push(int number) {
        if (used == ChunkList.chunkSize) {
            chunks = chunks.addChunk();
            used = 0;
            chunkcount++;
        }

        chunks.buffer[used++] = number;
    }

    public int size() {
        return (chunkcount-1)*20 + used;
    }
}
