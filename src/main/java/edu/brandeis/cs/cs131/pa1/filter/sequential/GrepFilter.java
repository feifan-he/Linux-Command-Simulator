package edu.brandeis.cs.cs131.pa1.filter.sequential;

/**
 * GrepFilter is used to support grep command.
 * Returns all lines from piped input that contain <query>.
 */
public class GrepFilter extends SequentialFilter {
    private final String query;

    /**
     * Constructor
     * @param query to be matched from the piped input
     */
    GrepFilter(String query) {
        this.query = query;
    }

    /**
     * Take a line and match the query
     * @param line the line got from the input queue
     * @return the line if there is a match else return null
     */
    @Override
    protected String processLine(String line) {
        return line.contains(this.query) ? line : null;
    }
}
