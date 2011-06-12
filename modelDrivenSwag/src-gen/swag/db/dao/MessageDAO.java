package swag.db.dao;

import javax.persistence.*;
import swag.db.model.Message;

public class MessageDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void initializeEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		MessageDAO.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void delete(Message message) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(message);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Message get(Integer key) {
		Message message = entityManager.find(Message.class, key);
		return message;
	}

	public void persist(Message message) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(message);
			entityManager.getTransaction().commit();

		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public Message update(Message message) {
		Message merged;
		entityManager.getTransaction().begin();
		try {
			merged = entityManager.merge(message);
			entityManager.getTransaction().commit();

			return merged;
		} catch (RuntimeException e) {
			rollBackTransaction(entityManager);
			throw e;
		}
	}

	public void refresh(Message message) {
		try {
			entityManager.refresh(message);
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
