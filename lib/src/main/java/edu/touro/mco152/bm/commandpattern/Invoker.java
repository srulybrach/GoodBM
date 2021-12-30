package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.Observer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Executor for the command pattern and the subject for the observer pattern
 */
public class Invoker { //also called executor
    /*
     * single class that can execute either a run test or a read test
     */
    Command writeCommand;
    Command readCommand;

    /**
     * Internal list of observers that get added to with register() and all get called with inform()
     */
    public ArrayList<Observer> listOfObservers = new ArrayList<>();
    public Invoker(){}

    public Invoker(Command writeCommand, Command readCommand){
        this.writeCommand = writeCommand;
        this.readCommand = readCommand;
    }

    /**
     * runs read test
     * @return true when test completes
     * @throws IOException
     */
    public boolean readTestCommand() throws IOException {
        return readCommand.execute();
    }

    /**
     * runs write test
     * @return true when the test completes
     * @throws IOException
     */
    public boolean writeTestCommand() throws IOException {
        return writeCommand.execute();
    }

    /**
     * registers an observer into the list of observers
     * @param observer observer that will be added to the list
     */
    public void register(Observer observer){
        listOfObservers.add(observer);
    }

    /**
     * Updates every observer in the list of observers
     */
    public void inform(){
        for(Observer observer : listOfObservers)
            observer.update();
    }
}
