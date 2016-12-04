package org.insysu.groceryproject.persistence.dao;

import org.insysu.groceryproject.persistence.entity.Cuisine;
import org.springframework.stereotype.Repository;

/**
 * Created by Souler on 2016/11/30.
 */
@Repository
public class CuisineDaoImplementation extends AbstractJPADao<Cuisine> implements CuisineDao {
    public CuisineDaoImplementation() {
        super();
        setInnerClass(Cuisine.class);
    }
}
