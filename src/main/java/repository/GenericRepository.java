package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
	
	public T findById(Object id, String[] IncludeProperties) {
		/* To obtain the class with all the associated classes (Joins) in a single 
		query we mark it in "IncludeProperties" saving the name of each attribute that we want */
		if(IncludeProperties != null) {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery(entityClass);
			Root<T> root = query.from(entityClass);
			for(String propertyName : IncludeProperties) {
				root.fetch(propertyName, JoinType.LEFT);
			}
			query.select(root).where(builder.equal(root.get("id"), id));
			return entityManager.createQuery(query).getSingleResult();
		}
		else {
			return entityManager.find(entityClass, id);
		}
	}

	public List<T> findAll(String[] IncludeProperties) {
		/* To obtain the classes with all the associated classes (Joins) in a single 
		query we mark it in "IncludeProperties" saving the name of each attribute that we want */
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		if(IncludeProperties != null) {
			for(String propertyName : IncludeProperties) {
				root.fetch(propertyName, JoinType.LEFT);
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

	public void delete(Object id) {
		T entity = findById(id);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}
	
	public T findById(Object id) {
		return this.findById(id, null);
	}
	public List<T> findAll(){
		return this.findAll(null);
	}
}