package com.xzs.dao;

import com.xzs.entity.Classify;
import java.util.List;

public interface ClassifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Classify record);

    Classify selectByPrimaryKey(Integer id);

    List<Classify> selectAll();

    int updateByPrimaryKey(Classify record);
}