package ir.librarymanagement.util;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerSingleton {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private EntityManagerSingleton() {}
    public static EntityManagerFactory getEntityManagerFactory() {
            synchronized (EntityManagerSingleton.class) {
                if (entityManagerFactory == null) {
                    entityManagerFactory = Persistence.createEntityManagerFactory("jdbc-postgres");
                }
            }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        synchronized (EntityManagerSingleton.class) {
            if (entityManager == null) {
                return getEntityManagerFactory().createEntityManager();
            }
        }
        return entityManager;
    }
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
