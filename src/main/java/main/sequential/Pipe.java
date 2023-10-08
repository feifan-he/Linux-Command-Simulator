package main.sequential;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class is used by filters to write output and read input.
 * <p>
 * You should not modify this class.
 *
 * @author Chami Lamelas and Eitan Joseph
 */
public class Pipe {

    public class PipeItem {
        private final String data;

        public PipeItem(String data) {
            this.data = data;
        }
    }

    BlockingQueue<PipeItem> buffer;


    public Pipe() {
        buffer = new LinkedBlockingQueue<>();
    }

    /**
     * Removes the element at the front of the pipe
     *
     * @return the element at the front of the pipe
     */
    public String read() {
        try {
            return buffer.take().data;
        } catch (InterruptedException e) {
        }
        return null;
    }

    /**
     * Adds an element to the pipe
     *
     * @param data to be added to the pipe
     */
    public void write(String data) {
        try {
            buffer.put(new PipeItem(data));
        } catch (InterruptedException e) {
        }
    }

}
