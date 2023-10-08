package main.sequential;

import main.CurrentWorkingDirectory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * RedirectFilter is used to support > command.
 * Redirect output from one command to be the input of the next command.
 */
public class RedirectFilter extends SequentialFilter {
    private final BufferedWriter writer;

    /**
     * Constructor, open a file for writing
     * @param fileName the file for writing
     * @throws IOException when file cannot be written
     */
    RedirectFilter(String fileName) throws IOException {
        String filePath = CurrentWorkingDirectory.get() + CurrentWorkingDirectory.getPathSeparator() + fileName;
        writer = new BufferedWriter(new FileWriter(filePath, false));
    }

    /**
     * Write everything from the input pipe to a file
     */
    @Override
    public void process() {
        super.process();
        // close file after done
        try {
            writer.close();
        } catch (IOException e) {
        }
    }

    /**
     * Write one line to the file
     * @param line the line got from the input queue
     * @return null
     */
    @Override
    protected String processLine(String line) {
        try {
            writer.write(line + "\n");
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
