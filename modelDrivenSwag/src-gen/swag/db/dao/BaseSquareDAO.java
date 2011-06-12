package swag.db.dao;

import javax.persistence.*;
import swag.db.model.BaseSquare;

public class BaseSquareDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		BaseSquareDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(BaseSquare baseSquare) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(baseSquare);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public BaseSquare get(Integer key) {
		BaseSquare baseSquare = entityManager.find(BaseSquare.class, key);
		return baseSquare;
	}

	public void persist(BaseSquare baseSquare) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(baseSquare);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public BaseSquare update(BaseSquare baseSquare) {
		BaseSquare merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(baseSquare);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(BaseSquare baseSquare) {
		try {
			entityManager.refresh(baseSquare);
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
