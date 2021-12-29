package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.BenchMarkOutput;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * Everything needed to run the write test command
 */
public class RunWriteTest implements Command{
    private Tests testType;
    private BenchMarkOutput outputter;
    private int numOfBlocks;
    private int numOfMarks;
    private int blockSizeKb;
    DiskRun.BlockSequence blockSequence;

    /**
     * Constructor that accepts everything needed to run write test
     * @param outputter where the results of the benchmark will output
     * @param numOfBlocks
     * @param numOfMarks
     * @param blockSizeKb
     * @param blockSequence
     * @param testType
     */
    public RunWriteTest(BenchMarkOutput outputter, int numOfBlocks, int numOfMarks, int blockSizeKb, DiskRun.BlockSequence blockSequence, Tests testType){
        this.testType = testType;
        this.outputter = outputter;
        this.numOfBlocks = numOfBlocks;
        this.blockSizeKb = blockSizeKb;
        this.numOfMarks = numOfMarks;
        this.blockSequence = blockSequence;
    }

    /**
     * Runs the write test based on the parameters from the constructor
     */
    @Override
    public boolean execute() {
        return testType.writeTest(outputter, numOfBlocks, numOfMarks, blockSizeKb, blockSequence);
    }
}
