package edu.touro.mco152.bm;

import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
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
        App.dataDir = new File(App.locationDir.getAbsolutePath()+File.separator+App.DATADIRNAME);

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

    //checks that the benchmark app can enter into it's state
    @Test
    void startBenchmark() throws Exception {
        setupDefaultAsPerProperties();
        assertNotNull(App.state);
    }

    //Make sure that the progress bar doesn't go negative
    //as for these purposes that wouldn't make sense.
    @Test
    void accurateProgress(){
        setupDefaultAsPerProperties();
        App.init();
        assertTrue(Gui.progressBar.getAlignmentX() >= 0);
        assertTrue(Gui.progressBar.getAlignmentY() >= 0);
    }

    //Test that a read and write test are possible
    @Test
    void completed(){
        //Arrange and act
        setupDefaultAsPerProperties();

        //Assert
        assertTrue(App.readTest);
        assertTrue(App.writeTest);
    }

    //Test that it's configured properly to be handed to DiskWorker.
    @Test
    void actedProperly(){
        //Arrange and act
        setupDefaultAsPerProperties();
        //Assert
        assertEquals(App.numOfBlocks, 256);
        assertEquals(App.numOfMarks, 50);
        assertEquals(App.blockSizeKb, 128);
    }
}