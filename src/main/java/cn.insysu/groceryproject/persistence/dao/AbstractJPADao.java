package cn.insysu.groceryproject.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        entityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

}
