package cn.insysu.groceryproject.persistence.dao;

import cn.insysu.groceryproject.persistence.entity.Cuisine;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
