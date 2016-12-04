package org.insysu.groceryproject.persistence.dao;

import org.insysu.groceryproject.persistence.entity.DealContent;

import java.util.List;

/**
 * Created by Souler on 2016/12/1.
 */
public interface DealContentDao {
    DealContent findOne(long id);
    List<DealContent> findAll();
    void create(DealContent entity);
    DealContent update(DealContent entity);
    void delete(DealContent entity);
    void deleteById(long entityId);
}