package swag.db.dao;

import javax.persistence.*;
import swag.db.model.Base;

public class BaseDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		BaseDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(Base base) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(base);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Base get(Integer key) {
		Base base = entityManager.find(Base.class, key);
		return base;
	}

	public void persist(Base base) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(base);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Base update(Base base) {
		Base merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(base);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(Base base) {
		try {
			entityManager.refresh(base);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	private void rollBackTransaction(EntityManager entityManager) {
		if (entityManager.getTransaction().isActive())
			entityManager.getTransaction().rollback();
	}

	public static void shutdown(EntityManager entityManager) {
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
	}

}
