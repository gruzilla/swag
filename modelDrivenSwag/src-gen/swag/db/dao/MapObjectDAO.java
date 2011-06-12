package swag.db.dao;

import javax.persistence.*;
import swag.db.model.MapObject;

public class MapObjectDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		MapObjectDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(MapObject mapObject) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(mapObject);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public MapObject get(Integer key) {
		MapObject mapObject = entityManager.find(MapObject.class, key);
		return mapObject;
	}

	public void persist(MapObject mapObject) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(mapObject);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public MapObject update(MapObject mapObject) {
		MapObject merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(mapObject);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(MapObject mapObject) {
		try {
			entityManager.refresh(mapObject);
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
