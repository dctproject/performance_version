package cn.insysu.groceryproject.persistence.dao;

import cn.insysu.groceryproject.persistence.entity.Cuisine;

import java.util.List;

/**
 * Created by Souler on 2016/11/30.
 */
public interface CuisineDao {
    Cuisine findOne(long id);
    List<Cuisine> findAll();
    void create(Cuisine entity);
    Cuisine update(Cuisine entity);
    void delete(Cuisine entity);
    void deleteById(long entityId);
}
