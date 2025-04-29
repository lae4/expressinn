package cn.zwz.express.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.express.entity.PackageType;
import cn.zwz.express.service.IPackageTypeService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "快递包装类型管理接口")
@RequestMapping("/zwz/packageType")
@Transactional
public class PackageTypeController {

    @Autowired
    private IPackageTypeService iPackageTypeService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条快递包装类型")
    public Result<PackageType> get(@RequestParam String id){
        return new ResultUtil<PackageType>().setData(iPackageTypeService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递包装类型个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iPackageTypeService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递包装类型")
    public Result<List<PackageType>> getAll(){
        return new ResultUtil<List<PackageType>>().setData(iPackageTypeService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询快递包装类型")
    public Result<IPage<PackageType>> getByPage(@ModelAttribute PackageType packageType ,@ModelAttribute PageVo page){
        QueryWrapper<PackageType> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(packageType.getTitle())) {
            qw.like("title",packageType.getTitle());
        }
        if(!ZwzNullUtils.isNull(packageType.getStatus())) {
            qw.eq("status",packageType.getStatus());
        }
        IPage<PackageType> data = iPackageTypeService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<PackageType>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改快递包装类型")
    public Result<PackageType> saveOrUpdate(PackageType packageType){
        if(iPackageTypeService.saveOrUpdate(packageType)){
            return new ResultUtil<PackageType>().setData(packageType);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增快递包装类型")
    public Result<PackageType> insert(PackageType packageType){
        if(Objects.equals(0,packageType.getSortOrder().compareTo(BigDecimal.ZERO))) {
            packageType.setSortOrder(BigDecimal.valueOf(iPackageTypeService.count() + 1L));
        }
        iPackageTypeService.saveOrUpdate(packageType);
        return new ResultUtil<PackageType>().setData(packageType);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑快递包装类型")
    public Result<PackageType> update(PackageType packageType){
        iPackageTypeService.saveOrUpdate(packageType);
        return new ResultUtil<PackageType>().setData(packageType);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除快递包装类型")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iPackageTypeService.removeById(id);
        }
        return ResultUtil.success();
    }
}
