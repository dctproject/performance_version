package cn.insysu.groceryproject.persistence.dao;

import cn.insysu.groceryproject.persistence.entity.Dish;

import java.util.List;

/**
 * Created by Souler on 2016/11/30.
 */
public interface DishDao {
    Dish findOne(long id);
    List<Dish> findAll();
    void create(Dish entity);
    Dish update(Dish entity);
    void delete(Dish entity);
    void deleteById(long entityId);
}
