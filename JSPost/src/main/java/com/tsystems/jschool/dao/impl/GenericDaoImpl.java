/**
 * 
 */
package com.tsystems.jschool.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tsystems.jschool.auxiliary.QueryConstant;
import com.tsystems.jschool.dao.GenericDao;
import com.tsystems.jschool.model.Entity;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * @author Lilia Abdulina
 * @param <T>
 *            a type variable
 */
public class GenericDaoImpl<T extends Entity> implements GenericDao<T> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	public static final String PERSISTENCE_UNIT_NAME = "ApplicationEntityManager";

	/**
	 * Entity manager, injected by Spring using @PersistenceContext annotation
	 * on setEntityManager()
	 */
	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	private Class<T> persistentClass;

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 * @param entityManager
	 *            the configured EntityManager for JPA implementation.
	 */
	public GenericDaoImpl(final Class<T> persistentClass,
			EntityManager entityManager) {
		this.persistentClass = persistentClass;
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return this.entityManager.createQuery(
				String.format(QueryConstant.QUERY_SELECT_ALL,
						persistentClass.getSimpleName())).getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked" })
	public List<T> getAllDistinct(String sortedBy, boolean asc) {
		String query = addSortedProtertiesToQuery(
				String.format(QueryConstant.QUERY_SELECT_ALL,
						persistentClass.getSimpleName()), sortedBy, asc);
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * @param asc
	 *            if true, then sort by increase, else decrease
	 */
	protected static String addSortedProtertiesToQuery(String query,
			String sortedBy, boolean asc) {
		StringBuilder str = new StringBuilder(query);
		str.append(" ORDER BY ");
		str.append(sortedBy);

		if (asc) {
			str.append(" ASC");
		} else {
			str.append(" DESC");
		}
		return str.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public T get(Integer id) {
		T entity = this.entityManager.find(this.persistentClass, id);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists(Integer id) {
		T entity = this.entityManager.find(this.persistentClass, id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public T save(T entity) {
		T savedEntity = null;

		if (entity.getId() == null) {
			entityManager.persist(entity);
			savedEntity = entity;
		} else {
			savedEntity = entityManager.merge(entity);
		}
		return savedEntity;
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(T entity) {
		if (!entityManager.contains(entity)) {
			T mergeEntity = entityManager.merge(entity);
			entityManager.remove(mergeEntity);
		} else {
			entityManager.remove(entity);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(Integer id) {
		this.entityManager.remove(this.get(id));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T getSingleResultOrNull(Query query) {
		List resultList = query.getResultList();
		if (resultList == null || resultList.size() == 0) {
			return null;
		}
		return (T) resultList.get(0);
	}
}