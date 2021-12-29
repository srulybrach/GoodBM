package edu.touro.mco152.bm.externalsys;

import edu.touro.mco152.bm.Observer;

/**
 * Observer that takes advantage of SlackManager
 */
public class SlackObserver implements Observer {
    double max;
    double average;
    SlackManager slackmgr = new SlackManager("BadBM");
    public SlackObserver(double max, double average){
        this.max = max;
        this.average = average;
    }

    /**
     * updates on Slack if the benchmark average is 3% greater than the average
     */
    @Override
    public void update() {
        double threePercentOverAverage = average * 1.03;
        if(average > threePercentOverAverage){
            slackmgr.postMsg2OurChannel(":frown: Benchmark max was over 3 percent average");
        }

    }
}
