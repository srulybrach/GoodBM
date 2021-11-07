package edu.touro.mco152.bm.persist;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import static edu.touro.mco152.bm.persist.EM.*;
import static org.junit.jupiter.api.Assertions.*;


/** RIGHT
 *  makes sure that getEntityManager successfully creates an Entity Manager object
 */
class EMTest {
    //Arrange
    EntityManager em = null;
    @Test
    void getEntityManagerTest() {
        //Act
        em = getEntityManager();

        //Assert
        assertNotNull(em);
    }
}