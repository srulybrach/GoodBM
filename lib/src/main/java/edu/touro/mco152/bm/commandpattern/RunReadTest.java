package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.output.BenchMarkOutput;
import edu.touro.mco152.bm.persist.DiskRun;

import java.io.IOException;

/**
 * Everything needed to run the reading test command
 */
public class RunReadTest implements Command{
    private Tests testType;
    private BenchMarkOutput outputter;
    private int numOfBlocks;
    private int numOfMarks;
    private int blockSizeKb;
    DiskRun.BlockSequence blockSequence;

    /**
     * Constructor that accepts everything necessary to run the read test.
     * @param outputter
     * @param numOfBlocks
     * @param numOfMarks
     * @param blockSizeKb
     * @param blockSequence
     * @param testType whether it is a read test or a write test
     */
    public RunReadTest(BenchMarkOutput outputter, int numOfBlocks, int numOfMarks, int blockSizeKb, DiskRun.BlockSequence blockSequence, Tests testType){
        this.testType = testType;
        this.outputter = outputter;
        this.numOfBlocks = numOfBlocks;
        this.blockSizeKb = blockSizeKb;
        this.numOfMarks = numOfMarks;
        this.blockSequence = blockSequence;
    }


    /**
     * Runs read test based on parameters given in constructor
     */
    @Override
    public boolean execute() throws IOException {
        return testType.readTest(outputter, numOfBlocks, numOfMarks, blockSizeKb, blockSequence);
    }
}
