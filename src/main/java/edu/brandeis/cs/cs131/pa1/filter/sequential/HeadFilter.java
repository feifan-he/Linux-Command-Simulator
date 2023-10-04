package edu.brandeis.cs.cs131.pa1.filter.sequential;

/**
 * HeadFilter is used to support head command.
 * Returns up to the first 10 lines from piped input.
 */
public class HeadFilter extends SequentialFilter {
    /**
     * Read up to the first 10 lines, and write to output pipe
     */
    @Override
    public void process() {
        for (int i = 0; i < 10 && !input.isEmpty(); i++) {
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
