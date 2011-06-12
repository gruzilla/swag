package swag.db.dao;

import javax.persistence.*;
import swag.db.model.TroopBuildingTypes;

public class TroopBuildingTypesDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		TroopBuildingTypesDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(TroopBuildingTypes troopBuildingTypes) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(troopBuildingTypes);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public TroopBuildingTypes get(Integer key) {
		TroopBuildingTypes troopBuildingTypes = entityManager.find(
				TroopBuildingTypes.class, key);
		return troopBuildingTypes;
	}

	public void persist(TroopBuildingTypes troopBuildingTypes) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(troopBuildingTypes);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public TroopBuildingTypes update(TroopBuildingTypes troopBuildingTypes) {
		TroopBuildingTypes merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(troopBuildingTypes);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(TroopBuildingTypes troopBuildingTypes) {
		try {
			entityManager.refresh(troopBuildingTypes);
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
