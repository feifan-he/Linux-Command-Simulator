package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;

import java.io.File;

/**
 * ListFilter is used to support ls command.
 * Lists the files in the current working directory
 */
public class ListFilter extends SequentialFilter {
    /**
     * List files in the current directory, and write the names to the output pipe
     */
    @Override
    public void process() {
        File directory = new File(CurrentWorkingDirectory.get());
        File[] filesAndFolders = directory.listFiles();
        if (filesAndFolders != null)
            for (File fileOrFolder : filesAndFolders) {
                this.output.write(fileOrFolder.getName());
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
