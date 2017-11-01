package ${basePackage}.${businessName}.controller;

import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.${businessName}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import com.xzs.common.domain.ResultList;
import javax.annotation.Resource;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* Created by ${author} on ${date}.
*/
@RestController
//@RequestMapping("${baseRequestMapping}")
@Api(value = "${modelNameUpperCamel}",description = "${swaagerRemark}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

	@ApiOperation(value = "添加${swaagerRemark}")
    @PostMapping("${baseRequestMapping}")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "删除${swaagerRemark}")
    @DeleteMapping("${baseRequestMapping}")
    public Result delete(@RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "更新${swaagerRemark}")
    @PutMapping("${baseRequestMapping}")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }
	@ApiOperation(value = "获取${swaagerRemark}")
    @GetMapping("${baseRequestMapping}")
    public Result<${modelNameUpperCamel}> detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }
	@ApiOperation(value = "获取全部${swaagerRemark}")
    @GetMapping("${baseRequestMapping}s")
    public Result<ResultList<${modelNameUpperCamel}>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(ResultList.genResultList(pageInfo));
    }
}
