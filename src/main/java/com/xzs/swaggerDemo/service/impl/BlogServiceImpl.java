package com.xzs.swaggerDemo.service.impl;

import com.xzs.dao.BlogMapper;
import com.xzs.entity.Blog;
import com.xzs.swaggerDemo.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xzs on 2017/11/01.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    
    /**
     *	持久化
     */
     @Override
    public void save(Blog model){
    	blogMapper.insert(model);
    }
    
    //批量持久化
    @Override
    public void save(List<Blog> models){
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
    public void update(Blog model){
    	blogMapper.updateByPrimaryKey(model);
    }
    
    //通过ID查找
    @Override
    public Blog findById(Integer id){
    	return blogMapper.selectByPrimaryKey(id);
    }
    
    //通过多个ID查找//eg：ids -> “1,2,3,4”
    @Override
    public List<Blog> findByIds(String ids){
    	return null;
    }
    
    //获取所有
    @Override
    public List<Blog> findAll(){
    	return blogMapper.selectAll();
    }

}
