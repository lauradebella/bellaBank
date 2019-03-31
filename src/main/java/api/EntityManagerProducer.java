package api;

import org.glassfish.hk2.api.Factory;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class EntityManagerProducer implements Factory<EntityManager> {

    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        factory = Persistence.createEntityManagerFactory("bank");
    }

    @Override
    public EntityManager provide() {
        return factory.createEntityManager();
    }

    @Override
    public void dispose(EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }

    }
}