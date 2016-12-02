package cn.insysu.groceryproject.persistence.dao;

import cn.insysu.groceryproject.persistence.entity.Deal;
import org.springframework.stereotype.Repository;

/**
 * Created by Souler on 2016/12/1.
 */
@Repository
public class DealDaoImplementation extends AbstractJPADao<Deal> implements DealDao {
    public DealDaoImplementation() {
        super();
        setInnerClass(Deal.class);
    }
}
