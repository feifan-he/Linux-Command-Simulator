package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;

/**
 * WorkingDirectoryFilter is used to support pwd command.
 * Returns current working directory.
 */
public class WorkingDirectoryFilter extends SequentialFilter {
    /**
     * write current working directory to output
     */
    @Override
    public void process() {
        this.output.write(CurrentWorkingDirectory.get());
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
