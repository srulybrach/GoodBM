package edu.touro.mco152.bm;

import edu.touro.mco152.bm.commandpattern.BenchMarkOutput;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Barebones implements BenchMarkOutput {
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
