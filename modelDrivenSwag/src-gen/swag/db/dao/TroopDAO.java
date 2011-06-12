package swag.db.dao;

import javax.persistence.*;
import swag.db.model.Troop;

public class TroopDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		TroopDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(Troop troop) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(troop);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Troop get(Integer key) {
		Troop troop = entityManager.find(Troop.class, key);
		return troop;
	}

	public void persist(Troop troop) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(troop);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Troop update(Troop troop) {
		Troop merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(troop);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(Troop troop) {
		try {
			entityManager.refresh(troop);
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
