package cn.insysu.groceryproject.persistence.dao;

import cn.insysu.groceryproject.persistence.entity.DealContent;
import org.springframework.stereotype.Repository;

/**
 * Created by Souler on 2016/12/1.
 */
@Repository
public class DealContentDaoImplementation extends AbstractJPADao<DealContent> implements DealContentDao {
    public DealContentDaoImplementation() {
        super();
        setInnerClass(DealContent.class);
    }
}