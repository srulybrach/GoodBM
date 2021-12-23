package edu.touro.mco152.bm.commandpattern;
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

    public void readTestCommand(){
        readCommand.execute();
    }

    public void writeTestCommand(){
        writeCommand.execute();
    }
}
