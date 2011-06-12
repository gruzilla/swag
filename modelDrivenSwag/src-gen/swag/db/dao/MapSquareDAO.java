package swag.db.dao;

import javax.persistence.*;
import swag.db.model.MapSquare;

public class MapSquareDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		MapSquareDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(MapSquare mapSquare) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(mapSquare);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public MapSquare get(Integer key) {
		MapSquare mapSquare = entityManager.find(MapSquare.class, key);
		return mapSquare;
	}

	public void persist(MapSquare mapSquare) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(mapSquare);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public MapSquare update(MapSquare mapSquare) {
		MapSquare merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(mapSquare);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(MapSquare mapSquare) {
		try {
			entityManager.refresh(mapSquare);
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
