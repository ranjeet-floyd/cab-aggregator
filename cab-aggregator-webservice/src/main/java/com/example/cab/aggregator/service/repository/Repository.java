package com.example.cab.aggregator.service.repository;

import java.util.Collection;

/**
 * CRUD for Rider
 *
 * @author ranjeet
 */
public interface Repository<T> {

    boolean delete(int id);

    boolean create(T entity);

    boolean update(int id, T newEntity);

    T get(int id);

    Collection<T> getAll();
}
