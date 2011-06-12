package swag.db.dao;

import javax.persistence.*;
import swag.db.model.SquareBoost;

public class SquareBoostDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		SquareBoostDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(SquareBoost squareBoost) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(squareBoost);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public SquareBoost get(Integer key) {
		SquareBoost squareBoost = entityManager.find(SquareBoost.class, key);
		return squareBoost;
	}

	public void persist(SquareBoost squareBoost) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(squareBoost);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public SquareBoost update(SquareBoost squareBoost) {
		SquareBoost merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(squareBoost);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(SquareBoost squareBoost) {
		try {
			entityManager.refresh(squareBoost);
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
