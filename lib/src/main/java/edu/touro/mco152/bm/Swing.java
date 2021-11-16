package edu.touro.mco152.bm;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class Swing extends SwingWorker<Boolean, DiskMark> implements BenchMarkOutput{

    @Override
    public boolean cancelled() {
        return isCancelled();
    }

    @Override
    public void setProgression(int progress) {
        setProgress(progress);
    }

    @Override
    public <V> void doPublish(DiskMark x) {
        publish(x);
    }

    @Override
    public boolean doEverything() {
        return false;
    }

    @Override
    public void addPropertyChangeListening(PropertyChangeListener x) {
        addPropertyChangeListener(x);
    }

    @Override
    public void executes() {
        execute();
    }

    @Override
    public boolean cancels(boolean x) {
        return cancel(x);
    }


    @Override
    protected Boolean doInBackground() throws Exception {
        return doEverything();
    }
}
