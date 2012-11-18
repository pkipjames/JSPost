/**
 * 
 */
package com.tsystems.jschool.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.jschool.model.Entity;

/**
 * Base class for running service tests.
 * 
 * @author Lilia Abdulina
 * 
 */
@ContextConfiguration(locations = { "classpath:root-context-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class BaseServiceTest<T extends Entity> {
	public static final String PERSISTENCE_UNIT_NAME = "ApplicationEntityManager";

	protected abstract GenericService<T> getService();

	protected abstract T getFirstEntity();

	protected abstract T getSecondEntity();

	protected T entity1;

	protected T entity2;

	protected Class<T> persistentClass;

	/**
	 * Entity manager, injected by Spring using @PersistenceContext annotation
	 * on setEntityManager()
	 */
	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	protected EntityManager entityManager;

	public BaseServiceTest(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#save(com.tsystems.jschool.model.Entity)}
	 * .
	 */
	@Before
	public void testSave() {
		entity1 = getService().save(getFirstEntity());
		entity2 = getService().save(getSecondEntity());

		Assert.assertNotNull(entity1);
		Assert.assertNotNull(entity2);
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#delete(com.tsystems.jschool.model.Entity)}
	 * .
	 */
	@After
	public void testDelete() {
		if (entity1 != null) {
			getService().delete(entity1);
		}
		if (entity2 != null) {
			getService().delete(entity2);
		}

	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#getEntityById(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetEntityById() {
		T entity = getService().getEntityById(entity1.getId());
		Assert.assertEquals(entity.getId(), entity1.getId());
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#getEntityById(java.lang.Integer)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetEntityByIdIllegalArgument() {
		getService().getEntityById(null);
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#getAllEntities()}
	 * .
	 */
	@Test
	public void testGetAllEntities() {
		List<T> list = getService().getAllEntities();

		assertTrue(list.contains(entity1));
		assertTrue(list.contains(entity2));
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#getSortedEntities(java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testGetSortedEntities() {
		List<T> list = getService().getAllEntities();

		assertTrue(list.contains(entity1));
		assertTrue(list.contains(entity2));
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#addSortedProtertiesToQuery(java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testAddSortedProtertiesToQuery() {
		// TODO
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#getSingleResultOrNull(javax.persistence.Query)}
	 * .
	 */
	/*
	 * @Test public void testGetSingleResultOrNull() { Query q =
	 * entityManager.createQuery(
	 * String.format("SELECT x FROM %s x WHERE x.id = :id",
	 * persistentClass.getSimpleName())).setParameter("id", entity1.getId()); T
	 * result = getSingleResultOrNull(q);
	 * 
	 * assertNotNull(result); }
	 *//**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.GenericServiceImpl#getSingleResultOrNull(javax.persistence.Query)}
	 * .
	 */
	/*
	 * @Test public void testGetSingleResultOrNullReturnNull() { Query q =
	 * entityManager.createQuery(
	 * String.format("SELECT x FROM %s x WHERE x.id = :id",
	 * persistentClass.getSimpleName())).setParameter("id", 0); T result =
	 * getSingleResultOrNull(q);
	 * 
	 * assertNull(result); }
	 * 
	 * @SuppressWarnings({ "unchecked", "rawtypes" }) private T
	 * getSingleResultOrNull(Query query) { List resultList =
	 * query.getResultList(); if (resultList == null || resultList.size() == 0)
	 * { return null; } return (T) resultList.get(0); }
	 */

}
