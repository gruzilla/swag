package swag.db.dao;

import javax.persistence.*;
import swag.db.model.ResourceBuilding;

public class ResourceBuildingDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		ResourceBuildingDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(ResourceBuilding resourceBuilding) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(resourceBuilding);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public ResourceBuilding get(Integer key) {
		ResourceBuilding resourceBuilding = entityManager.find(
				ResourceBuilding.class, key);
		return resourceBuilding;
	}

	public void persist(ResourceBuilding resourceBuilding) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(resourceBuilding);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public ResourceBuilding update(ResourceBuilding resourceBuilding) {
		ResourceBuilding merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(resourceBuilding);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(ResourceBuilding resourceBuilding) {
		try {
			entityManager.refresh(resourceBuilding);
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
