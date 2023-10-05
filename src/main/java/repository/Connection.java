package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    private static Connection instancia = null;
    private static EntityManagerFactory emf;
    private static EntityManager em;

    private Connection(){}

    public static Connection getInstance() {
        if (instancia == null) {
            instancia = new Connection();
            emf = Persistence.createEntityManagerFactory("entrenamosuy");
        }
        return instancia;
    }

    public EntityManager getEntityManager() {
        if(em == null || !em.isOpen()){
            em = emf.createEntityManager();
        }
        return em;
    }

    public void shutDown() {
        if(em != null && em.isOpen()){ em.close(); }
        emf.close();
    }

    public void closeEM(){
        em.close();
    }
}
