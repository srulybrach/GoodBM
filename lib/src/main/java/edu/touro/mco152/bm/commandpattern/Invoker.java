package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.Observer;

import java.io.IOException;
import java.util.ArrayList;

public class Invoker { //also called executor
    /*
     * single class that can execute either a run test or a read test
     */
    Command writeCommand;
    Command readCommand;

    ArrayList<Observer> listOfObservers = new ArrayList<>();
    Invoker(){}

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

    public void register(Observer observer){
        listOfObservers.add(observer);
    }
    public void inform(){
        for(Observer observer : listOfObservers)
            observer.update();
    }
}
