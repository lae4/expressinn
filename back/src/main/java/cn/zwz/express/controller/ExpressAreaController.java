package cn.zwz.express.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.express.entity.ExpressArea;
import cn.zwz.express.service.IExpressAreaService;
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
@Api(tags = "快递区域管理接口")
@RequestMapping("/zwz/expressArea")
@Transactional
public class ExpressAreaController {

    @Autowired
    private IExpressAreaService iExpressAreaService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条快递区域")
    public Result<ExpressArea> get(@RequestParam String id){
        return new ResultUtil<ExpressArea>().setData(iExpressAreaService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递区域个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iExpressAreaService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递区域")
    public Result<List<ExpressArea>> getAll(){
        return new ResultUtil<List<ExpressArea>>().setData(iExpressAreaService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询快递区域")
    public Result<IPage<ExpressArea>> getByPage(@ModelAttribute ExpressArea expressArea ,@ModelAttribute PageVo page){
        QueryWrapper<ExpressArea> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(expressArea.getTitle())) {
            qw.like("title",expressArea.getTitle());
        }
        if(!ZwzNullUtils.isNull(expressArea.getStatus())) {
            qw.like("status",expressArea.getStatus());
        }
        IPage<ExpressArea> data = iExpressAreaService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<ExpressArea>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改快递区域")
    public Result<ExpressArea> saveOrUpdate(ExpressArea expressArea){
        if(iExpressAreaService.saveOrUpdate(expressArea)){
            return new ResultUtil<ExpressArea>().setData(expressArea);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增快递区域")
    public Result<ExpressArea> insert(ExpressArea expressArea){
        if(Objects.equals(0,expressArea.getSortOrder().compareTo(BigDecimal.ZERO))) {
            expressArea.setSortOrder(BigDecimal.valueOf(iExpressAreaService.count() + 1L));
        }
        iExpressAreaService.saveOrUpdate(expressArea);
        return new ResultUtil<ExpressArea>().setData(expressArea);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑快递区域")
    public Result<ExpressArea> update(ExpressArea expressArea){
        iExpressAreaService.saveOrUpdate(expressArea);
        return new ResultUtil<ExpressArea>().setData(expressArea);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除快递区域")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iExpressAreaService.removeById(id);
        }
        return ResultUtil.success();
    }
}
