package com.xzs.swaggerDemo.service.impl;

import com.xzs.dao.ClassifyMapper;
import com.xzs.entity.Classify;
import com.xzs.swaggerDemo.service.ClassifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xzs on 2017/11/01.
 */
@Service
@Transactional
public class ClassifyServiceImpl implements ClassifyService {
    @Resource
    private ClassifyMapper classifyMapper;
    
    /**
     *	持久化
     */
     @Override
    public void save(Classify model){
    	classifyMapper.insert(model);
    }
    
    //批量持久化
    @Override
    public void save(List<Classify> models){
    }
    
    //通过主鍵刪除
    @Override
    public void deleteById(Integer id){
    }
    
    //批量刪除 eg：ids -> “1,2,3,4”
    @Override
    public void deleteByIds(String ids){
    }
    
    //更新
    @Override
    public void update(Classify model){
    	classifyMapper.updateByPrimaryKey(model);
    }
    
    //通过ID查找
    @Override
    public Classify findById(Integer id){
    	return classifyMapper.selectByPrimaryKey(id);
    }
    
    //通过多个ID查找//eg：ids -> “1,2,3,4”
    @Override
    public List<Classify> findByIds(String ids){
    	return null;
    }
    
    //获取所有
    @Override
    public List<Classify> findAll(){
    	return classifyMapper.selectAll();
    }

}
