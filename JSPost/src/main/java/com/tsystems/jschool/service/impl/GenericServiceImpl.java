package com.tsystems.jschool.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.jschool.dao.GenericDao;
import com.tsystems.jschool.model.Entity;
import com.tsystems.jschool.service.GenericService;

@Transactional
public abstract class GenericServiceImpl<T extends Entity> implements
		GenericService<T>, Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * GenericDao instance, set by constructor of child classes
	 */
	protected GenericDao<T> dao;

	public GenericServiceImpl() {
	}

	public GenericServiceImpl(GenericDao<T> genericDao) {
		this.dao = genericDao;
	}

	@Override
	public T save(T entity) throws IllegalArgumentException {
		if (entity == null) {
			String message = "Entity for saving can not be null";
			log.warn(message);
			throw new IllegalArgumentException(message);
		}
		return dao.save(entity);
	}

	@Override
	public void delete(T entity) throws IllegalArgumentException {
		if (entity == null) {
			String message = "Entity for delete can not be null";
			log.warn(message);
			throw new IllegalArgumentException(message);
		}
		dao.remove(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public T getEntityById(Integer id) throws IllegalArgumentException {
		if (id == null) {
			String message = "Argument id can not be null";
			log.warn(message);
			throw new IllegalArgumentException(message);
		}
		return dao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllEntities() {
		return dao.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getSortedEntities(String sortedBy, boolean asc) {
		if (sortedBy.isEmpty()) {
			String message = "Argument sortedBy can not be empty";
			log.warn(message);
			throw new IllegalArgumentException(message);
		}
		return dao.getAllDistinct(sortedBy, asc);
	}		
}
