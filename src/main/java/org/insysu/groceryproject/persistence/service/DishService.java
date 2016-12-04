package org.insysu.groceryproject.persistence.service;

import org.insysu.groceryproject.persistence.dao.DishDao;
import org.insysu.groceryproject.persistence.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Souler on 2016/12/1.
 */
@Service
@Transactional
public class DishService {
    @Autowired
    private DishDao dao;

    public DishService() { super(); }

    public void create(final Dish entity) { dao.create(entity); }
    @Cacheable("dish")
    public Dish findOne(final long id) { return dao.findOne(id); }
    @Cacheable("dishlist")
    public List<Dish> findAll() { return dao.findAll(); }
}
