package cn.insysu.groceryproject.persistence.dao;
import cn.insysu.groceryproject.persistence.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Souler on 2016/11/30.
 */
@Repository
public class UserDaoImplementation extends AbstractJPADao<User> implements UserDao {
    public UserDaoImplementation() {
        super();
        setInnerClass(User.class);
    }

    @Override
    public User findByName(String name) {
        List<User> kii = findByStatement("username" , name);
        if (kii != null) {
            return kii.get(0);
        } else {
            return null;
        }
    }
}
