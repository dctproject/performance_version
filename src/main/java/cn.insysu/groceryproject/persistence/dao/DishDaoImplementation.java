package cn.insysu.groceryproject.persistence.dao;

import cn.insysu.groceryproject.persistence.entity.Dish;

/**
 * Created by Souler on 2016/11/30.
 */
public class DishDaoImplementation extends AbstractJPADao<Dish> implements DishDao {
    public DishDaoImplementation() {
        super();
        setInnerClass(Dish.class);
    }
}
