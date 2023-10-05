package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.JoinType;
import java.util.List;

public class GenericRepository<T> {
	@PersistenceContext
	private final EntityManager entityManager;
	private final Class<T> entityClass;

	public GenericRepository(EntityManager entityManager, Class<T> entityClass) {
		this.entityManager = entityManager;
		this.entityClass = entityClass;
	}

	public T findById(Object id, String keyAttributeName, String[] IncludeProperties) {
		/* 
		To obtain the class with all the associated classes (Joins) in a single 
		query we mark it in "IncludeProperties" saving the name of each attribute that we want.
		Also to use this operation we have to specify the name of the entity key.
		 */
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		if (IncludeProperties != null) {
			for(String property : IncludeProperties) {
				if(property.split("\\.").length == 1) {
					root.fetch(property, JoinType.LEFT);
				}
				else {
					root = addJoinProperties(root, property, JoinType.LEFT);
				}
			}
		}
		query.select(root).where(builder.equal(root.get(keyAttributeName), id));
		try {
			T entity = entityManager.createQuery(query).getSingleResult();
			return entity;
		}
		catch (Exception e ) {
			System.out.println(e);
			return null;
		}
		
	}

	public List<T> findAll(String[] IncludeProperties) {
		/* 
		To obtain the class with all the associated classes (Joins) in a single 
		query we mark it in "IncludeProperties" saving the name of each attribute that we want.
		 */
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);

		if (IncludeProperties != null) {
			for(String property : IncludeProperties) {
				if(property.split("\\.").length == 1) {
					root.fetch(property, JoinType.LEFT);
				}
				else {
					root = addJoinProperties(root, property, JoinType.LEFT);
				}
			}
		}
		query.select(root);
		return entityManager.createQuery(query).getResultList();
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(Object id, String keyAtributeName) {
		T entity = findById(id, keyAtributeName);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}

	private Root<T> addJoinProperties(Root<T> root, String property, JoinType joinType) {
		/*
		 This method solves the problem n+1 up to concatenations of 6 joins, 
		 I couldn't generalize it, it broke in every place
		 */
		String[] propertyParts = property.split("\\.");
		switch (propertyParts.length) {
		case 2 -> 
		root.fetch(propertyParts[0], joinType)
		.fetch(propertyParts[1], joinType);
		case 3 -> 
		root.fetch(propertyParts[0], joinType)
		.fetch(propertyParts[1], joinType)
		.fetch(propertyParts[2], joinType);
		case 4 -> 
		root.fetch(propertyParts[0], joinType)
		.fetch(propertyParts[1], joinType)
		.fetch(propertyParts[2], joinType)
		.fetch(propertyParts[3], joinType);
		case 5 -> 
		root.fetch(propertyParts[0], joinType)
		.fetch(propertyParts[1], joinType)
		.fetch(propertyParts[2], joinType)
		.fetch(propertyParts[3], joinType)
		.fetch(propertyParts[4], joinType);
		case 6 -> 
		root.fetch(propertyParts[0], joinType)
		.fetch(propertyParts[1], joinType)
		.fetch(propertyParts[2], joinType)
		.fetch(propertyParts[3], joinType)
		.fetch(propertyParts[4], joinType)
		.fetch(propertyParts[5], joinType);
		}
		return root;
	}

	public T findById(Object id, String keyAtributeName) {
		// This should be handled by a catch statement
		return this.findById(id, keyAtributeName, null);
	}
	public List<T> findAll(){
		return this.findAll(null);
	}
}