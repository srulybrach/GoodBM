package edu.touro.mco152.bm;

import edu.touro.mco152.bm.output.Barebones;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DiskWorkerTest {
    /**
     * Bruteforce setup of static classes/fields to allow DiskWorker to run.
     *
     * @author lcmcohen
     */

    private void setupDefaultAsPerProperties()
    {
        /// Do the minimum of what  App.init() would do to allow to run.
        Gui.mainFrame = new MainFrame();
        App.p = new Properties();
        App.loadConfig();
        System.out.println(App.getConfigString());
        Gui.progressBar = Gui.mainFrame.getProgressBar(); //must be set or get Nullptr

        // configure the embedded DB in .jDiskMark
        System.setProperty("derby.system.home", App.APP_CACHE_DIR);

        // code from startBenchmark
        //4. create data dir reference
        App.dataDir = new File("/private/var/folders/ml/3pr0wp_x5z7g6yb3qhj9_v5m0000gp/T/badBM2325896966925335521/");

        //5. remove existing test data if exist
        if (App.dataDir.exists()) {
            if (App.dataDir.delete()) {
                App.msg("removed existing data dir");
            } else {
                App.msg("unable to remove existing data dir");
            }
        }
        else
        {
            App.dataDir.mkdirs(); // create data dir if not already present
        }
    }


    Barebones barebones = new Barebones();
    DiskWorker diskWorker = new DiskWorker(barebones);



    //checks that the benchmark app can enter into it's state
    @Test
    void startBenchmark() throws Exception {
        setupDefaultAsPerProperties();
        diskWorker.doEverything();
        assertNotNull(barebones.mbps);
    }

    //Make sure that the progress bar doesn't go negative
    //as for these purposes that wouldn't make sense.
    @Test
    void accurateProgress() throws Exception {
        setupDefaultAsPerProperties();
        diskWorker.doEverything();
        ArrayList<Integer> arrayList = barebones.list;
        for(int i = 0; i < 50; i++){
            assertTrue(arrayList.contains((Integer) i));
        }
    }

    //Test that a read and write test are possible
    @Test
    void completed() throws Exception {
        setupDefaultAsPerProperties();
        assertTrue(diskWorker.doEverything());
    }
}