package cn.insysu.groceryproject.persistence.dao;
import cn.insysu.groceryproject.persistence.entity.User;

import java.util.List;

/**
 * Created by Souler on 2016/11/30.
 */
public interface UserDao  {
    User findOne(long id);
    User findByName(String name);
    List<User> findAll();
    void create(User entity);
    User update(User entity);
    void delete(User entity);
    void deleteById(long entityId);
}
