package swag.db.dao;

import javax.persistence.*;
import swag.db.model.Map;

public class MapDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		MapDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(Map map) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(map);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Map get(Integer key) {
		Map map = entityManager.find(Map.class, key);
		return map;
	}

	public void persist(Map map) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(map);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Map update(Map map) {
		Map merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(map);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(Map map) {
		try {
			entityManager.refresh(map);
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
