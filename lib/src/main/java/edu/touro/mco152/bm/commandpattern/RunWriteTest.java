package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.BenchMarkOutput;
import edu.touro.mco152.bm.persist.DiskRun;

public class RunWriteTest implements Command{
    /*
     * Everything needed in order to run a write test
     */

    private Tests testType;
    private BenchMarkOutput outputter;
    private int numOfBlocks;
    private int numOfMarks;
    private int blockSizeKb;
    DiskRun.BlockSequence blockSequence;


    public RunWriteTest(BenchMarkOutput outputter, int numOfBlocks, int numOfMarks, int blockSizeKb, DiskRun.BlockSequence blockSequence, Tests testType){
        this.testType = testType;
        this.outputter = outputter;
        this.numOfBlocks = numOfBlocks;
        this.blockSizeKb = blockSizeKb;
        this.numOfMarks = numOfMarks;
        this.blockSequence = blockSequence;
    }

    /*
     * Runs the write test based on the parameters from the constructor
     */
    @Override
    public boolean execute() {
        return testType.writeTest(outputter, numOfBlocks, numOfMarks, blockSizeKb, blockSequence);
    }
}
