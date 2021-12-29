package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.output.Barebones;
import edu.touro.mco152.bm.ui.Gui;
import edu.touro.mco152.bm.ui.MainFrame;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static edu.touro.mco152.bm.persist.DiskRun.BlockSequence.SEQUENTIAL;
import static org.junit.jupiter.api.Assertions.*;

class CommandTests {
    void setupDefaultAsPerProperties()
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

    Barebones outputter = new Barebones();
    Tests tester = new Tests();
    RunReadTest readTester = new RunReadTest(outputter, 128, 25, 2048, SEQUENTIAL, tester);
    RunWriteTest writeTester = new RunWriteTest(outputter, 128, 25, 2048, SEQUENTIAL, tester);
    Invoker executor = new Invoker(writeTester, readTester);


    /*
     * Tests the run and read commands capabilities.
     * Does NOT test for accurate results or any functionality beyond the method not crashing.
     * Just tests that the command methods can be run without causing error. For a deeper test
     * check out DiskWorkerTest.
     */
    @Test
    void writeTestTester() throws IOException {
        setupDefaultAsPerProperties();
        assertTrue(executor.writeTestCommand());
    }

    //test a read tests functionality
    @Test
    void readTestTester() throws IOException {
        setupDefaultAsPerProperties();
        assertTrue(executor.readTestCommand());
    }

}