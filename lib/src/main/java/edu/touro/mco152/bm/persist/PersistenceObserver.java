package edu.touro.mco152.bm.persist;

import edu.touro.mco152.bm.Observer;
import jakarta.persistence.EntityManager;

public class PersistenceObserver implements Observer {
    DiskRun diskRun;
    public PersistenceObserver(DiskRun diskRun){
        this.diskRun = diskRun;
    }
    @Override
    public void update() {
        EntityManager em = EM.getEntityManager();
        em.getTransaction().begin();
        em.persist(diskRun);
        em.getTransaction().commit();
    }
}
