package org.insysu.groceryproject.persistence.dao;

import org.insysu.groceryproject.persistence.entity.Deal;

import java.util.List;

/**
 * Created by Souler on 2016/12/1.
 */
public interface DealDao {
    Deal findOne(long id);
    List<Deal> findAll();
    void create(Deal entity);
    Deal update(Deal entity);
    void delete(Deal entity);
    void deleteById(long entityId);
}