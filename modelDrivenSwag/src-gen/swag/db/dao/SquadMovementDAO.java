package swag.db.dao;

import javax.persistence.*;
import swag.db.model.SquadMovement;

public class SquadMovementDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		SquadMovementDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(SquadMovement squadMovement) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(squadMovement);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public SquadMovement get(Integer key) {
		SquadMovement squadMovement = entityManager.find(SquadMovement.class,
				key);
		return squadMovement;
	}

	public void persist(SquadMovement squadMovement) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(squadMovement);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public SquadMovement update(SquadMovement squadMovement) {
		SquadMovement merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(squadMovement);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(SquadMovement squadMovement) {
		try {
			entityManager.refresh(squadMovement);
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
