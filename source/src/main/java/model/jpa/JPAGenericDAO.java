package model.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.dao.GenericDAO;

public class JPAGenericDAO<T,ID> implements GenericDAO<T, ID> {
	/* Attributes */
	private Class<T> persistenceClass;	
	protected EntityManager entityManager;
	
	/* Constructor */
	public JPAGenericDAO(Class<T> persistenceClass) {
		this.persistenceClass = persistenceClass;
		entityManager = Persistence.createEntityManagerFactory("mascotinder").createEntityManager();
	}
	
	/* Methods */
	
	/* CRUD methods */
	@Override
	public void create(T entity) {
		entityManager.getTransaction().begin();
		
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		}
		catch (Exception ex) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}
	}
	
	@Override
	public T read(ID id) {
		return entityManager.find(persistenceClass, id);
	}

	@Override
	public void update(T entity) {
		entityManager.getTransaction().begin();
		
		try {
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
		}
		catch (Exception ex) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}
	}

	@Override
	public void delete(T entity) {
		entityManager.getTransaction().begin();
		
		try {
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
		}
		catch (Exception ex) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}
	}

}
