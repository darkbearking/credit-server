package com.zjzy.credit.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.zjzy.credit.common.model.page.Pageable;
import com.zjzy.credit.common.model.page.Pagination;
import com.zjzy.credit.server.dao.DummyDao;
import com.zjzy.credit.server.model.Dummy;
import com.zjzy.credit.server.service.DummyService;

import tk.mybatis.mapper.entity.Example;

/**
 * @title DummyServiceImpl
 * @description Dummy service 实现类 
 * @author zhiwei.han
 * @date 2019年8月12日
 */
@Service
public class DummyServiceImpl implements DummyService {
    @Autowired
    private DummyDao dummyDao;

    /*
     * (non-Javadoc)
     * @see com.zjzy.credit.server.service.DummyService#allDummys()
     */
    @Override
    public List<Dummy> allDummys() {
        return dummyDao.selectAll();
    }

    /*
    * (non-Javadoc)
    * @see com.zjzy.credit.server.service.DummyService#queryByPage(java.lang.String, com.zjzy.credit.common.model.page.Pagination)
    */
    @Override
    @Pageable
    public Pagination<Dummy> queryByPage(String q, Pagination<Dummy> pagination) {
        Example example = new Example(Dummy.class);
        example.createCriteria().andLike("name", "%" + q + "%");
        List<Dummy> dummys = dummyDao.selectByExample(example);
        pagination.setDataset(dummys);
        pagination.setTotal(((Page<Dummy>) dummys).getTotal());
        return pagination;
    }

    /* (non-Javadoc)
     * @see com.zjzy.credit.server.service.DummyService#add(com.zjzy.credit.server.model.Dummy)
     */
    @Override
    @Transactional
    public int add(Dummy dummy) {
        return dummyDao.insert(dummy);
    }

}
