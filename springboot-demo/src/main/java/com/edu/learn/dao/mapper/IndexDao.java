package com.edu.learn.dao.mapper;

import com.edu.learn.dao.model.YhbModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndexDao {
    YhbModel getModel(YhbModel model);

    public  int inserYh(YhbModel model);
}
