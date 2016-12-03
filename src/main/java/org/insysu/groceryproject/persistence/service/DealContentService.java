package org.insysu.groceryproject.persistence.service;

import org.insysu.groceryproject.persistence.dao.DealContentDao;
import org.insysu.groceryproject.persistence.entity.DealContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Souler on 2016/12/1.
 */
@Service
@Transactional
public class DealContentService {
    @Autowired
    private DealContentDao dao;

    public DealContentService() {super();}

    public void create(final DealContent entity) { dao.create(entity); }

    public DealContent findOne(final long id) { return dao.findOne(id); }

    public List<DealContent> findAll() { return dao.findAll(); }

    public void delete(final DealContent entity) { dao.delete(entity); }

    public void deleteById(final long id) { dao.deleteById(id); }
}
