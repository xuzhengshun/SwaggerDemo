package com.xzs.swaggerDemo.controller;

import com.xzs.core.Result;
import com.xzs.core.ResultGenerator;
import com.xzs.entity.Admin;
import com.xzs.swaggerDemo.service.AdminService;
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
//@RequestMapping("/admin")
@Api(value = "Admin",description = "管理员表")
public class AdminController {
    @Resource
    private AdminService adminService;

	@ApiOperation(value = "添加管理员表")
    @PostMapping("/admin")
    public Result add(@RequestBody Admin admin) {
        adminService.save(admin);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "删除管理员表")
    @DeleteMapping("/admin")
    public Result delete(@RequestParam Integer id) {
        adminService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

	@ApiOperation(value = "更新管理员表")
    @PutMapping("/admin")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return ResultGenerator.genSuccessResult();
    }
	@ApiOperation(value = "获取管理员表")
    @GetMapping("/admin")
    public Result<Admin> detail(@RequestParam Integer id) {
        Admin admin = adminService.findById(id);
        return ResultGenerator.genSuccessResult(admin);
    }
	@ApiOperation(value = "获取全部管理员表")
    @GetMapping("/admins")
    public Result<ResultList<Admin>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Admin> list = adminService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(ResultList.genResultList(pageInfo));
    }
}
