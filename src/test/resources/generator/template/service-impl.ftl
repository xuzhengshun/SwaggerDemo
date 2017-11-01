package ${basePackage}.${businessName}.service.impl;

import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.${businessName}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;
    
    /**
     *	持久化
     */
     @Override
    public void save(${modelNameUpperCamel} model){
    	${modelNameLowerCamel}Mapper.insert(model);
    }
    
    //批量持久化
    @Override
    public void save(List<${modelNameUpperCamel}> models){
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
    public void update(${modelNameUpperCamel} model){
    	${modelNameLowerCamel}Mapper.updateByPrimaryKey(model);
    }
    
    //通过ID查找
    @Override
    public ${modelNameUpperCamel} findById(Integer id){
    	return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(id);
    }
    
    //通过多个ID查找//eg：ids -> “1,2,3,4”
    @Override
    public List<${modelNameUpperCamel}> findByIds(String ids){
    	return null;
    }
    
    //获取所有
    @Override
    public List<${modelNameUpperCamel}> findAll(){
    	return ${modelNameLowerCamel}Mapper.selectAll();
    }

}
