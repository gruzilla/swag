package swag.db.dao;

import javax.persistence.*;
import swag.db.model.Building;

public class BuildingDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		BuildingDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(Building building) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(building);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Building get(Integer key) {
		Building building = entityManager.find(Building.class, key);
		return building;
	}

	public void persist(Building building) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(building);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Building update(Building building) {
		Building merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(building);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(Building building) {
		try {
			entityManager.refresh(building);
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
