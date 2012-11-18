/**
 * 
 */
package com.tsystems.jschool.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * <p>
 * Extend this is guarantees typesafe (no casting necessary) DAO's for domain
 * objects.
 * 
 * @author Lilia Abdulina
 * @param <T>
 *            a type variable
 */
public interface GenericDao<T> {

	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAll();

	/**
	 * Gets all records without duplicates.
	 * <p>
	 * Note that if you use this method, it is imperative that your model
	 * classes correctly implement the hashcode/equals methods
	 * </p>
	 * @param asc 
	 * @param sortedBy 
	 * 
	 * @return List of populated objects
	 */
	List<T> getAllDistinct(String sortedBy, boolean asc);

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(Integer id);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(Integer id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	@Transactional
	T save(T object);

	/**
	 * Generic method to delete an object
	 * 
	 * @param entity
	 *            the object to remove
	 */
	void remove(T entity);

	/**
	 * @param query
	 * @return
	 */
	T getSingleResultOrNull(Query query);
}
