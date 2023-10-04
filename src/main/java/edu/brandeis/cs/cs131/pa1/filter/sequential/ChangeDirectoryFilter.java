package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ChangeDirectoryFilter is used to support cd command.
 * Changes current working directory to <dir>.
 */
public class ChangeDirectoryFilter extends SequentialFilter {
    private final String newPath;

    /**
     * Implemented cd filter to change current directory
     * @param path the target directory's path
     * @throws FileNotFoundException when the path is not a directory or the directory is not exist
     */
    ChangeDirectoryFilter(String path) throws FileNotFoundException {
        String fileSeparator = CurrentWorkingDirectory.getPathSeparator();
        List<String> finalPath = new ArrayList<>(Arrays.asList(CurrentWorkingDirectory.get().split(fileSeparator)));
        for (String part : path.split(fileSeparator)) {
            if (part.equals("..") && !finalPath.isEmpty()) {
                finalPath.remove(finalPath.size() - 1);
            } else if (!part.isEmpty() && !part.equals(".")) {
                finalPath.add(part);
            }
        }
        this.newPath = String.join(fileSeparator, finalPath);
        File f = new File(newPath);
        // throw the error to be handled in SequentialCommandBuilder
        if (!f.exists() || !f.isDirectory())
            throw new FileNotFoundException();
    }

    /**
     * Change CurrentWorkingDirectory to the new path
     */
    @Override
    public void process() {
        CurrentWorkingDirectory.setTo(newPath);
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
