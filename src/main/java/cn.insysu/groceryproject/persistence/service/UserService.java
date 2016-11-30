package cn.insysu.groceryproject.persistence.service;

import cn.insysu.groceryproject.persistence.dao.UserDao;
import cn.insysu.groceryproject.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Souler on 2016/11/30.
 */

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao dao;

    public UserService() {super();}

    public void create(final User entity) { dao.create(entity); }

    public User findOne(final long id) { return dao.findOne(id); }

    public List<User> findAll() { return dao.findAll(); }
}