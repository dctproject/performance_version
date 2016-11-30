package cn.insysu.groceryproject.persistence.dao;
import cn.insysu.groceryproject.persistence.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Souler on 2016/11/30.
 */
@Repository
public class UserDaoImplementation extends AbstractJPADao<User> implements UserDao {
    public UserDaoImplementation() {
        super();
        setInnerClass(User.class);
    }
}
