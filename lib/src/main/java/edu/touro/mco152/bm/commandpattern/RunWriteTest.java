package edu.touro.mco152.bm.commandpattern;

import edu.touro.mco152.bm.persist.DiskRun;

public class RunWriteTest implements Command{
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

    @Override
    public void execute() {
        testType.writeTest(outputter, numOfBlocks, numOfMarks, blockSizeKb, blockSequence);
    }
}
