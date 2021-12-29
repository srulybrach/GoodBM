package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.BenchMarkOutput;
import edu.touro.mco152.bm.persist.DiskRun;

import java.io.IOException;

public class RunReadTest implements Command{
    /*
     * Everything needed to run read test
     */
    private Tests testType;
    private BenchMarkOutput outputter;
    private int numOfBlocks;
    private int numOfMarks;
    private int blockSizeKb;
    DiskRun.BlockSequence blockSequence;

    public RunReadTest(BenchMarkOutput outputter, int numOfBlocks, int numOfMarks, int blockSizeKb, DiskRun.BlockSequence blockSequence, Tests testType){
        this.testType = testType;
        this.outputter = outputter;
        this.numOfBlocks = numOfBlocks;
        this.blockSizeKb = blockSizeKb;
        this.numOfMarks = numOfMarks;
        this.blockSequence = blockSequence;
    }


    /*
     * Runs read test based on parameters given in constructorw
     */
    @Override
    public boolean execute() throws IOException {
        return testType.readTest(outputter, numOfBlocks, numOfMarks, blockSizeKb, blockSequence);
    }
}
