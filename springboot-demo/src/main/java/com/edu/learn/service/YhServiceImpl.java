package com.edu.learn.service;

import com.edu.learn.dao.mapper.IndexDao;
import com.edu.learn.dao.model.YhbModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YhServiceImpl implements YhService {

    @Autowired
    private IndexDao dao;


    @Override
    public YhbModel getModel(YhbModel model) {
        return dao.getModel(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int inserYh(YhbModel model) {
            dao.inserYh(model);
            int a = 1/0;
            return  1;
    }
}
