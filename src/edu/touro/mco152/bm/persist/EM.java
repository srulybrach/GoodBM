/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.touro.mco152.bm.persist;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author James
 */
public class EM {

    private static EntityManager em = null;

    public static EntityManager getEntityManager() {
        if (em == null) {
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("jDiskMarkPU");
                em = emf.createEntityManager();
            } catch (Exception exc)
            {
                System.err.println("EM: FATAL Error initializing persistence: " + exc.getMessage());
                System.err.println("Make sure persistence.xml is accessible");
                System.exit(4);
            }
        }
        return em;
    }
}
