package swag.db.dao;

import javax.persistence.*;
import swag.db.model.Squad;

public class SquadDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		SquadDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(Squad squad) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(squad);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Squad get(Integer key) {
		Squad squad = entityManager.find(Squad.class, key);
		return squad;
	}

	public void persist(Squad squad) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(squad);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Squad update(Squad squad) {
		Squad merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(squad);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(Squad squad) {
		try {
			entityManager.refresh(squad);
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
