package edu.touro.mco152.bm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static edu.touro.mco152.bm.Util.*;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


class UtilTest {


    /** Cross-check
     *  Tests if the deleteDirectory works, it does so by cross checking through the creation of a new one, if the
     *  creation of the directory again returns true it means that the first one was successfully deleted.
     */
    @Test
    void deleteDirectoryRightTest() {
        //arrange
        File directory = new File("/testDirectory/java");
        System.out.println(directory.mkdirs());

        //act
        deleteDirectory(directory);

        //assert
        assertTrue(directory.mkdirs());
    }

    @Test
    /**Error-Condition && Boundary Existence
     * Tests if the method correctly throws NullPointerException when given a null directory to delete
     */
    void deleteDirectoryBoundaryTest() {
        //arrange
        File directory = null;

        //act and assert
        assertThrows(NullPointerException.class, () -> deleteDirectory(directory));
    }

    /** Boundary - Range -
     *  Tests if randInt can handle very high numbers
     *  BROKEN by giving it too big of a range to create an int within (replace the 0 with 1 to make it work again)
     */
    @ParameterizedTest
    @ValueSource(ints= {10, 100, Integer.MAX_VALUE})
    void testRandInt(int number){
        //arrange
        int results;

        //act
        results = randInt(0, number);

        //assert
        assertTrue(results >= 0 && results <= number);
    }

    /**Performance -
     * Makes sure the creation of random integers doesn't take too long. Around 1ms for 10 integers is to be expected.
     * Boundary - Performance -
     * Checks if performance starts to drop when given an absurdly large amount of numbers to create.
     */
    @Test
    void randIntTimer(){
        assertTimeout(ofMillis(1000), () -> {
            for (int i = 0; i < 10000; i++) {
                randInt(1, Integer.MAX_VALUE);
            }
        });
    }

}