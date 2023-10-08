package main.sequential;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TailFilter is used to support tail command.
 * Returns up to the last 10 lines from piped input.
 */
public class TailFilter extends SequentialFilter {
    /**
     * Read up to the last 10 lines, and write to output pipe
     */
    @Override
    public void process() {
        Queue<String> tail = new LinkedList<>();
        String s;
        while ((s = input.read()) != null) {
            tail.add(s);
            if (tail.size() > 10) {
                tail.remove();
            }
        }
        for (String tailS : tail) {
            output.write(tailS);
        }
        output.write(null);
    }

    /**
     * Implement the method but will not be used
     * @param line the line got from the input queue
     * @return null
     */
    @Override
    protected String processLine(String line) {
        return null;
    }
}
