package edu.touro.mco152.bm;

public class TestObserver implements Observer{
    public boolean flag = false;
    @Override
    public void update() {
        flag = true;
    }
}
