package com.xzs.swaggerDemo.service.impl;

import com.xzs.dao.AdminMapper;
import com.xzs.entity.Admin;
import com.xzs.swaggerDemo.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xzs on 2017/11/01.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    
    /**
     *	持久化
     */
     @Override
    public void save(Admin model){
    	adminMapper.insert(model);
    }
    
    //批量持久化
    @Override
    public void save(List<Admin> models){
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
    public void update(Admin model){
    	adminMapper.updateByPrimaryKey(model);
    }
    
    //通过ID查找
    @Override
    public Admin findById(Integer id){
    	return adminMapper.selectByPrimaryKey(id);
    }
    
    //通过多个ID查找//eg：ids -> “1,2,3,4”
    @Override
    public List<Admin> findByIds(String ids){
    	return null;
    }
    
    //获取所有
    @Override
    public List<Admin> findAll(){
    	return adminMapper.selectAll();
    }

}
