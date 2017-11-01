package com.xzs.swaggerDemo.controller;

import com.xzs.core.Result;
import com.xzs.core.ResultGenerator;
import com.xzs.entity.Classify;
import com.xzs.swaggerDemo.service.ClassifyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import com.xzs.common.domain.ResultList;
import javax.annotation.Resource;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* Created by xzs on 2017/11/01.
*/
@RestController
//@RequestMapping("/classify")
@Api(value = "Classify",description = "分类表")
public class ClassifyController {
    @Resource
    private ClassifyService classifyService;

	@ApiOperation(value = "添加分类表")
    @PostMapping("/classify")
    public Result add(@RequestBody Classify classify) {
        classifyService.save(classify);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "删除分类表")
    @DeleteMapping("/classify")
    public Result delete(@RequestParam Integer id) {
        classifyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "更新分类表")
    @PutMapping("/classify")
    public Result update(@RequestBody Classify classify) {
        classifyService.update(classify);
        return ResultGenerator.genSuccessResult();
    }
	@ApiOperation(value = "获取分类表")
    @GetMapping("/classify")
    public Result<Classify> detail(@RequestParam Integer id) {
        Classify classify = classifyService.findById(id);
        return ResultGenerator.genSuccessResult(classify);
    }
	@ApiOperation(value = "获取全部分类表")
    @GetMapping("/classifys")
    public Result<ResultList<Classify>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Classify> list = classifyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(ResultList.genResultList(pageInfo));
    }
}
