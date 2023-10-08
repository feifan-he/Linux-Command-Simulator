package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main implementation of the REPL loop (read-eval-print loop). It reads
 * commands from the user, parses them, executes them and displays the result.
 */
public class SequentialREPL {

    /**
     * The main method that will execute the REPL loop
     *
     * @param args not used
     */
    public static void main(String[] args) {

        Scanner consoleReader = new Scanner(System.in);
        System.out.print(Message.WELCOME);

        while (true) {
            System.out.print(Message.NEWCOMMAND);

            // read user command, if its just whitespace, skip to next command
            String cmd = consoleReader.nextLine();
            if (cmd.trim().isEmpty()) {
                continue;
            }

            // exit the REPL if user specifies it
            if (cmd.trim().equals("exit")) {
                break;
            } else {
                try {
                    List<SequentialFilter> filtersList = SequentialCommandBuilder.createFiltersFromCommand(cmd);
                    List<Thread> threads = new ArrayList<>();
                    // process each filter
                    for (SequentialFilter filter : filtersList) {
                        Thread t = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                filter.process();
                            }
                        });
                        t.start();
                        threads.add(t);
                    }

                    for(Thread t : threads) {
                        t.join();
                    }

                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        }
        System.out.print(Message.GOODBYE);
        consoleReader.close();
    }
}
