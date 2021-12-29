package edu.touro.mco152.bm;

import edu.touro.mco152.bm.DiskMark;

import java.beans.PropertyChangeListener;

/**
 * Interface for the different ways the benchmark can be outputted
 */
public interface BenchMarkOutput {
    boolean cancelled();
    void setProgression(int x);
    void doPublish(DiskMark x);
    //boolean doEverything() throws Exception;
    void addPropertyChangeListening(PropertyChangeListener x);
    void executes();
    boolean cancels(boolean x);
}
