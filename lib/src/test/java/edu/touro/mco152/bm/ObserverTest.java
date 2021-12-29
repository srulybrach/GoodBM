package edu.touro.mco152.bm;

import edu.touro.mco152.bm.commandpattern.Invoker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test barebones functionality of being able to 1. create observer object, 2. register that object, 3. inform that object
 */
class ObserverTest {
    @Test
    public void testObserver(){
        Invoker invoker = new Invoker();
        Observer testObserver = new TestObserver();
        invoker.register(testObserver);
        invoker.inform();
        assertTrue(((TestObserver) testObserver).flag);
    }
}