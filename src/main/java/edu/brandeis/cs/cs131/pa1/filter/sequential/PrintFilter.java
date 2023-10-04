package edu.brandeis.cs.cs131.pa1.filter.sequential;

/**
 * PrintFilter is used to support STDOUT.
 */
public class PrintFilter extends SequentialFilter {
    /**
     * Print line to STDOUT
     * @param line the line got from the input queue
     * @return null
     */
    @Override
    protected String processLine(String line) {
        System.out.println(line);
        return null;
    }
}
