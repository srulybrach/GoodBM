package edu.touro.mco152.bm;

import java.beans.PropertyChangeListener;

public interface BenchMarkOutput {
    boolean cancelled();
    void setProgression(int x);
    void doPublish(DiskMark x);
    void addPropertyChangeListening(PropertyChangeListener x);
    void executes();
    boolean cancels(boolean x);
}
