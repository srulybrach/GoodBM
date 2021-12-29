package edu.touro.mco152.bm;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Outputter that saves the outputs of the benchmark internally rather than displaying it externally.
 */
public class Barebones implements BenchMarkOutput {
    /*
     * barebones (or non swing) implementation of BenchmarkOutput
     */
    ArrayList<Integer> list = new ArrayList();
    int average;
    int mbps;


    @Override
    public boolean cancelled() {
        return false;
    }

    @Override
    public void setProgression(int x) {
        list.add(x);
    }

    @Override
    public void doPublish(DiskMark x) {
        mbps = (int) x.getBwMbSec();
        average = (int) x.getCumAvg();
    }

    @Override
    public void addPropertyChangeListening(PropertyChangeListener x) {

    }

    @Override
    public void executes() {

    }

    @Override
    public boolean cancels(boolean x) {
        return false;
    }


}
