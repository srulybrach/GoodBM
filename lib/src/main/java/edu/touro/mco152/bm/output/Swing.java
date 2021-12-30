package edu.touro.mco152.bm.output;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.DiskWorker;
import edu.touro.mco152.bm.Util;
import edu.touro.mco152.bm.ui.Gui;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;

import static edu.touro.mco152.bm.App.dataDir;

public class Swing extends SwingWorker<Boolean, DiskMark> implements BenchMarkOutput {
    DiskWorker worker;

    public void setSwing(DiskWorker worker){
        this.worker = worker;
    }

    @Override
    public boolean cancelled() {
        return isCancelled();
    }

    @Override
    public void setProgression(int progress) {
        setProgress(progress);
    }

    @Override
    public void doPublish(DiskMark x) {
        publish(x);
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
        return worker.doEverything();
    }

    @Override
    protected void process(List<DiskMark> markList) {
        markList.stream().forEach((dm) -> {
            if (dm.type == DiskMark.MarkType.WRITE) {
                Gui.addWriteMark(dm);
            } else {
                Gui.addReadMark(dm);
            }
        });
    }

    @Override
    protected void done() {
        if (App.autoRemoveData) {
            Util.deleteDirectory(dataDir);
        }
        App.state = App.State.IDLE_STATE;
        Gui.mainFrame.adjustSensitivity();
    }
}
