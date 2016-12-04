package org.insysu.groceryproject.persistence.service;

import org.insysu.groceryproject.persistence.dao.DealDao;
import org.insysu.groceryproject.persistence.entity.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Souler on 2016/12/1.
 */
@Service
@Transactional
public class DealService {
    @Autowired
    private DealDao dao;

    public DealService() {super();}

    public void create(final Deal entity) { dao.create(entity); }
    @Cacheable("deal")
    public Deal findOne(final long id) { return dao.findOne(id); }
    @Cacheable("deallist")
    public List<Deal> findAll() { return dao.findAll(); }

    public void delete(final Deal entity) { dao.delete(entity); }

    public void deleteById(final long id) { dao.deleteById(id); }
    @CachePut(cacheNames = "deal", key = "#entity.getOid()")
    public Deal update(final Deal entity) { return dao.update(entity); }
}
