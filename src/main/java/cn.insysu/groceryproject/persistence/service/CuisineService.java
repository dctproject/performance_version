package cn.insysu.groceryproject.persistence.service;

import cn.insysu.groceryproject.persistence.dao.CuisineDao;
import cn.insysu.groceryproject.persistence.entity.Cuisine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Souler on 2016/11/30.
 */
@Service
@Transactional
public class CuisineService {
    @Autowired
    private CuisineDao dao;

    public CuisineService() { super(); }

    public void create(final Cuisine entity) { dao.create(entity); }

    public Cuisine findOne(final long id) { return dao.findOne(id); }

    public List<Cuisine> findAll() { return dao.findAll(); }

    public Cuisine update(Cuisine entity) { return dao.update(entity); }

    public void delete(Cuisine entity) { dao.delete(entity); }

    public void deleteById(final long id) { dao.deleteById(id); }


}
