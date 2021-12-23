package edu.touro.mco152.bm.commandpattern;

import java.io.IOException;

public interface Command {
    boolean execute() throws IOException;
}
