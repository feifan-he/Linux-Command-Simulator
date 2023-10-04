package edu.brandeis.cs.cs131.pa1.filter.sequential;

/**
 * WordCountFilter is used to support wc command.
 * Counts the number of lines, words, and characters in the piped input.
 *
 */
public class WordCountFilter extends SequentialFilter {
    private int numLines, numWords, numChars;

    /**
     * Constructor, set numLines, numWords, and numChars to default 0
     */
    WordCountFilter() {
        numLines = 0;
        numWords = 0;
        numChars = 0;
    }

    /**
     * Process the input line by line, count the number of lines, words and characters
     */
    @Override
    public void process() {
        while (!input.isEmpty()) {
            numLines++;
            String line = input.read();
            numChars += line.length();
            if (!line.isEmpty())
                numWords += line.split(" ").length;
        }
        output.write(numLines + " " + numWords + " " + numChars);
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
