package swag.db.dao;

import javax.persistence.*;
import swag.db.model.Resources;

public class ResourcesDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		ResourcesDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(Resources resources) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(resources);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Resources get(Integer key) {
		Resources resources = entityManager.find(Resources.class, key);
		return resources;
	}

	public void persist(Resources resources) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(resources);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Resources update(Resources resources) {
		Resources merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(resources);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(Resources resources) {
		try {
			entityManager.refresh(resources);
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
