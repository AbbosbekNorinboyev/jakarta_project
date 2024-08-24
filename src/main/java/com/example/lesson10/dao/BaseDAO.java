package com.example.lesson10.dao;

import com.example.lesson10.entity.Auditable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDAO<T extends Auditable, Id extends Serializable> {
    protected static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("project");
    protected static final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private final Class<T> persistenceClass;

    @SuppressWarnings("unchecked")
    protected BaseDAO() {
        this.persistenceClass = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments())[0];
    }

    protected void begin() {
        entityManager.getTransaction().begin();
    }

    protected void commit() {
        entityManager.getTransaction().commit();
    }

    protected void rollback() {
        entityManager.getTransaction().rollback();
    }

    public T save(T entity) {
        begin();
        entityManager.persist(entity);
        commit();
        return entity;
    }

    public T findById(@NotNull Id id) {
        begin();
        T entity = entityManager.find(persistenceClass, id);
        commit();
        return entity;
    }

    public List<T> findAll() {
        begin();
        List<T> entities = entityManager.createQuery("from " + persistenceClass.getSimpleName(), persistenceClass).getResultList();
        commit();
        return entities;
    }

    public boolean update(T entity) {
        begin();
        entityManager.merge(entity);
        commit();
        return true;
    }

    public boolean deleteById(@NotNull Id id) {
        begin();
        entityManager.createQuery("delete from " + persistenceClass.getSimpleName() + " a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        commit();
        return true;
    }
}
