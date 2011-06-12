package swag.db.dao;

import javax.persistence.*;
import swag.db.model.TroopTypes;

public class TroopTypesDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		TroopTypesDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(TroopTypes troopTypes) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(troopTypes);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public TroopTypes get(Integer key) {
		TroopTypes troopTypes = entityManager.find(TroopTypes.class, key);
		return troopTypes;
	}

	public void persist(TroopTypes troopTypes) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(troopTypes);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public TroopTypes update(TroopTypes troopTypes) {
		TroopTypes merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(troopTypes);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(TroopTypes troopTypes) {
		try {
			entityManager.refresh(troopTypes);
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
