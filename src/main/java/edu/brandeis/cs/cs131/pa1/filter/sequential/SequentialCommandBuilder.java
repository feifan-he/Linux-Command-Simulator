package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.Message;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class manages the parsing and execution of a command. It splits the raw
 * input into separated subcommands, creates subcommand filters, and links them
 * into a list.
 */
public class SequentialCommandBuilder {
    /**
     * Initialize a hashmap where the key is the command and the value is whether the cmd has an input
     */
    private static final Map<String, Boolean> cmdHasInput = new HashMap<String, Boolean>() {{
        put("pwd", false);
        put("ls", false);
        put("cd", false);
        put("cat", false);
        put("head", true);
        put("tail", true);
        put("grep", true);
        put("wc", true);
        put("uniq", true);
        put(">", true);
    }};


    /**
     * Initialize a hashmap where the key is the command and the value is whether the cmd has a parameter
     */
    private static final Map<String, Boolean> cmdHasParam = new HashMap<String, Boolean>() {{
        put("pwd", false);
        put("ls", false);
        put("cd", true);
        put("cat", true);
        put("head", false);
        put("tail", false);
        put("grep", true);
        put("wc", false);
        put("uniq", false);
        put(">", true);
    }};


    /**
     * Validate and create filters from input command
     * @param command The command to be executed
     * @return A list of sequential filters
     * @throws Exception for command syntax and runtime errors
     */
    public static List<SequentialFilter> createFiltersFromCommand(String command) throws Exception {
        command = command.trim();
        if (command.startsWith(">"))
            throw new Exception(Message.REQUIRES_INPUT.with_parameter(command));

        command = command.replace(">", "|>");
        String[] subCommands = command.split("\\|");
        String cmdName = null;

        // validate commands
        for (int i = 0; i < subCommands.length; i++) {
            String subCommand = subCommands[i];
            String[] cmdParts = subCommand.trim().split(" ");
            cmdName = cmdParts[0];

            Message err = null;
            // if the command doesn't exist
            if (!cmdHasInput.containsKey(cmdName))
                err = Message.COMMAND_NOT_FOUND;
            // if command needs a parameter but none found
            else if (cmdParts.length == 1 && cmdHasParam.get(cmdName))
                err = Message.REQUIRES_PARAMETER;
            // the first command should not need an input
            else if (i == 0 && cmdHasInput.get(cmdName))
                err = Message.REQUIRES_INPUT;
            // the commands that are not the first command need inputs
            else if (i != 0 && !cmdHasInput.get(cmdName))
                err = Message.CANNOT_HAVE_INPUT;
            // cd should be a standalone command with no input and output
            else if (subCommands.length > 1 && cmdName.equals("cd"))
                err = Message.CANNOT_HAVE_OUTPUT;
            // redirect should only be the last subcommand
            else if (i > 0 && subCommands[i - 1].trim().startsWith(">"))
                throw new Exception(Message.CANNOT_HAVE_OUTPUT.with_parameter(subCommands[i - 1]));

            if (err != null)
                throw new Exception(err.with_parameter(subCommand.replace("|>", ">")));
        }

        // construct filter for each subcommand
        List<SequentialFilter> seqFilters = new ArrayList<>();
        for (String subCommand : subCommands)
            seqFilters.add(constructFilterFromSubCommand(subCommand));

        // if the last command is not redirect, add a printFilter
        if (!cmdName.equals(">"))
            seqFilters.add(new PrintFilter());

        // link seqFilters
        linkFilters(seqFilters);
        return seqFilters;
    }


    /**
     * Construct filter from subcommand
     * @param subCommand The command to be constructed
     * @return SequentialFilter correspond to the subcommand
     * @throws Exception for unexpected command
     */
    private static SequentialFilter constructFilterFromSubCommand(String subCommand) throws Exception {
        String[] elements = subCommand.trim().split(" ");
        String cmdName = elements[0];
        String parameter = null;
        if (elements.length == 2) {
            parameter = elements[1];
        }

        // check the cmdName and create a filter
        switch (cmdName) {
            case "cat":
                try {
                    return new CatFilter(parameter);
                } catch (FileNotFoundException e) {
                    throw new Exception(Message.FILE_NOT_FOUND.with_parameter(subCommand));
                }
            case "pwd":
                return new WorkingDirectoryFilter();
            case "grep":
                return new GrepFilter(parameter);
            case "head":
                return new HeadFilter();
            case "tail":
                return new TailFilter();
            case "cd":
                try {
                    return new ChangeDirectoryFilter(parameter);
                } catch (FileNotFoundException e) {
                    throw new Exception(Message.DIRECTORY_NOT_FOUND.with_parameter(subCommand));
                }
            case "ls":
                return new ListFilter();
            case "wc":
                return new WordCountFilter();
            case "uniq":
                return new UniqFilter();
            case ">":
                return new RedirectFilter(parameter);
            // if the command is not found
            default:
                throw new UnsupportedOperationException("UNIMPLEMENTED");
        }
    }


    /**
     * Link filters
     * @param filters A list of sequential filters to be linked
     */
    private static void linkFilters(List<SequentialFilter> filters) {
        SequentialFilter pre = null;
        for (SequentialFilter cur : filters) {
            if (pre != null) {
                pre.setNextFilter(cur);
            }
            pre = cur;
        }
    }
}
