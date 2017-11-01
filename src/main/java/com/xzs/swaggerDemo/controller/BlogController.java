package com.xzs.swaggerDemo.controller;

import com.xzs.core.Result;
import com.xzs.core.ResultGenerator;
import com.xzs.entity.Blog;
import com.xzs.swaggerDemo.service.BlogService;
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
//@RequestMapping("/blog")
@Api(value = "Blog",description = "博客表")
public class BlogController {
    @Resource
    private BlogService blogService;

	@ApiOperation(value = "添加博客表")
    @PostMapping("/blog")
    public Result add(@RequestBody Blog blog) {
        blogService.save(blog);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "删除博客表")
    @DeleteMapping("/blog")
    public Result delete(@RequestParam Integer id) {
        blogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "更新博客表")
    @PutMapping("/blog")
    public Result update(@RequestBody Blog blog) {
        blogService.update(blog);
        return ResultGenerator.genSuccessResult();
    }
	@ApiOperation(value = "获取博客表")
    @GetMapping("/blog")
    public Result<Blog> detail(@RequestParam Integer id) {
        Blog blog = blogService.findById(id);
        return ResultGenerator.genSuccessResult(blog);
    }
	@ApiOperation(value = "获取全部博客表")
    @GetMapping("/blogs")
    public Result<ResultList<Blog>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Blog> list = blogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(ResultList.genResultList(pageInfo));
    }
}
