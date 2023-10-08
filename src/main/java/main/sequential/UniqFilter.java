package main.sequential;

/**
 * UniqFilter is used to support uniq command.
 * Removes any line from the piped input that is the same as the previous line.
 */
public class UniqFilter extends SequentialFilter {
    /**
     * Remove any line that is same as the previous line
     */
    @Override
    public void process() {
        String prev = null, s;
        while ((s = input.read()) != null) {
            if (!s.equals(prev))
                output.write(s);
            prev = s;
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
