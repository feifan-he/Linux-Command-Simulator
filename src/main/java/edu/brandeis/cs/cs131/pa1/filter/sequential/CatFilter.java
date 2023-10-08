package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CatFilter is used to support cat command.
 * Reads <file>’s contents into pipe or STDOUT.
 */
public class CatFilter extends SequentialFilter {
    private final Scanner scanner;

    /**
     * Implemented cat filter to read file content
     * @param fileName to be read
     * @throws FileNotFoundException when the file cannot be found
     */
    public CatFilter(String fileName) throws FileNotFoundException {
        String filePath = CurrentWorkingDirectory.get() + CurrentWorkingDirectory.getPathSeparator() + fileName;
        scanner = new Scanner(new File(filePath));
    }

    @Override
    /**
     * Read all lines from the file and write to the output pipe
     */
    public void process() {
        while (scanner.hasNextLine())
            output.write(scanner.nextLine());
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
