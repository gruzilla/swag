package swag.db.dao;

import javax.persistence.*;
import swag.db.model.TroopBuilding;

public class TroopBuildingDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		TroopBuildingDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(TroopBuilding troopBuilding) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(troopBuilding);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public TroopBuilding get(Integer key) {
		TroopBuilding troopBuilding = entityManager.find(TroopBuilding.class,
				key);
		return troopBuilding;
	}

	public void persist(TroopBuilding troopBuilding) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(troopBuilding);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public TroopBuilding update(TroopBuilding troopBuilding) {
		TroopBuilding merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(troopBuilding);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(TroopBuilding troopBuilding) {
		try {
			entityManager.refresh(troopBuilding);
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
