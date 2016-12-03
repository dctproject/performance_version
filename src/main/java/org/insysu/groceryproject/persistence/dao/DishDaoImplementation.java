package org.insysu.groceryproject.persistence.dao;

import org.insysu.groceryproject.persistence.entity.Dish;
import org.springframework.stereotype.Repository;

/**
 * Created by Souler on 2016/11/30.
 */
@Repository
public class DishDaoImplementation extends AbstractJPADao<Dish> implements DishDao {
    public DishDaoImplementation() {
        super();
        setInnerClass(Dish.class);
    }
}
