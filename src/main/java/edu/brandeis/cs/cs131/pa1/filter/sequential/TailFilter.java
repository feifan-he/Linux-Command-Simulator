package edu.brandeis.cs.cs131.pa1.filter.sequential;

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
        while (input.size() > 10) {
            input.read();
        }
        while (!input.isEmpty()) {
            output.write(input.read());
        }
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
