package org.insysu.groceryproject.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJPADao<T extends Serializable> {

    private Class<T> innerClass;

    @PersistenceContext
    private EntityManager entityManager;

    public final void setInnerClass(final Class<T> classToSet) {
        this.innerClass = classToSet;
    }

    public T findOne(final long id) {
        return entityManager.find(innerClass, id);
    }

    public List<T> findByStatement(final String statement , final String data) {
//        if (type == "int") {
//            entityManager.createQuery("from " + innerClass.getName() + " where " + statement + "=" + Integer.parseInt(data));
//        } else if (type == "long") {
//            ene
//        } else if (type == "string") {
//
//        }
        Query q = entityManager.createQuery("from " + innerClass.getName() + " as p where p." + statement + "=:" + statement);
//        q.setParameter(1,data);
        q.setParameter(statement , data);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + innerClass.getName()).getResultList();
    }

    public void create(final T entity) {
        entityManager.persist(entity);
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

}
