package edu.touro.mco152.bm.commandpattern;

import java.io.IOException;

public class Invoker { //also called executor
    /*
     * single class that can execute either a run test or a read test
     */
    Command writeCommand;
    Command readCommand;
    public Invoker(Command writeCommand, Command readCommand){
        this.writeCommand = writeCommand;
        this.readCommand = readCommand;
    }

    public boolean readTestCommand() throws IOException {
        return readCommand.execute();
    }

    public boolean writeTestCommand() throws IOException {
        return writeCommand.execute();
    }
}
