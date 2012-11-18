package com.tsystems.jschool.service;

import java.util.List;

import com.tsystems.jschool.model.Entity;

/**
 * Generic Service that talks to GenericDao to CRUD POJOs.
 * 
 * The interface for the typesafe (no casting necessary) managers for domain
 * objects.
 * 
 * @author Lilia Abdulina
 * @param <T>
 *            a type variable
 */
public interface GenericService<T extends Entity> {

	T save(T entity) throws IllegalArgumentException;

	void delete(T entity) throws IllegalArgumentException;

	T getEntityById(Integer id) throws IllegalArgumentException;

	List<T> getAllEntities();

	List<T> getSortedEntities(String sortedBy, boolean asc);
}
