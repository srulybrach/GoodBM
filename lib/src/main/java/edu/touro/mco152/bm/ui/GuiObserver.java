package edu.touro.mco152.bm.ui;

import edu.touro.mco152.bm.Observer;
import edu.touro.mco152.bm.persist.DiskRun;

public class GuiObserver implements Observer {
    DiskRun diskRun;
    public GuiObserver(DiskRun diskRun){
        this.diskRun = diskRun;
    }
    @Override
    public void update() {
        Gui.runPanel.addRun(diskRun);
    }
}
