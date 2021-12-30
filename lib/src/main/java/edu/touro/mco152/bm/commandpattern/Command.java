package edu.touro.mco152.bm.commandpattern;

import java.io.IOException;

/**
 * Interface for all Commands to implement
 */
public interface Command {
    /**
     * Executes the command
     * @return true if command gets executed correctly
     * @throws IOException
     */
    boolean execute() throws IOException;
}
