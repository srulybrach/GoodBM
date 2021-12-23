package edu.touro.mco152.bm;

import edu.touro.mco152.bm.DiskMark;

import java.beans.PropertyChangeListener;

public interface BenchMarkOutput {
    /*
     * Interface that DiskWorker uses to output the results of the benchmark, general
     * purpose, can be used for Swing, CLI, or just raw data.
     */
    boolean cancelled();
    void setProgression(int x);
    void doPublish(DiskMark x);
    //boolean doEverything() throws Exception;
    void addPropertyChangeListening(PropertyChangeListener x);
    void executes();
    boolean cancels(boolean x);
}
