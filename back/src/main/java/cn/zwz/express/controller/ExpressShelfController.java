package cn.zwz.express.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.express.entity.ExpressArea;
import cn.zwz.express.entity.ExpressShelf;
import cn.zwz.express.service.IExpressAreaService;
import cn.zwz.express.service.IExpressShelfService;
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
@Api(tags = "快递货架管理接口")
@RequestMapping("/zwz/expressShelf")
@Transactional
public class ExpressShelfController {

    @Autowired
    private IExpressShelfService iExpressShelfService;

    @Autowired
    private IExpressAreaService iExpressAreaService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条快递货架")
    public Result<ExpressShelf> get(@RequestParam String id){
        return new ResultUtil<ExpressShelf>().setData(iExpressShelfService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递货架个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iExpressShelfService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递货架")
    public Result<List<ExpressShelf>> getAll(){
        return new ResultUtil<List<ExpressShelf>>().setData(iExpressShelfService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询快递货架")
    public Result<IPage<ExpressShelf>> getByPage(@ModelAttribute ExpressShelf expressShelf ,@ModelAttribute PageVo page){
        QueryWrapper<ExpressShelf> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(expressShelf.getAreaId())) {
            qw.eq("area_id",expressShelf.getAreaId());
        }
        if(!ZwzNullUtils.isNull(expressShelf.getTitle())) {
            qw.like("title",expressShelf.getTitle());
        }
        if(!ZwzNullUtils.isNull(expressShelf.getStatus())) {
            qw.eq("status",expressShelf.getStatus());
        }
        IPage<ExpressShelf> data = iExpressShelfService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<ExpressShelf>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改快递货架")
    public Result<ExpressShelf> saveOrUpdate(ExpressShelf expressShelf){
        if(iExpressShelfService.saveOrUpdate(expressShelf)){
            return new ResultUtil<ExpressShelf>().setData(expressShelf);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增快递货架")
    public Result<ExpressShelf> insert(ExpressShelf expressShelf){
        if(Objects.equals(0,expressShelf.getSortOrder().compareTo(BigDecimal.ZERO))) {
            expressShelf.setSortOrder(BigDecimal.valueOf(iExpressAreaService.count() + 1L));
        }
        ExpressArea area = iExpressAreaService.getById(expressShelf.getAreaId());
        if(area == null) {
            return ResultUtil.error("区域不存在");
        }
        expressShelf.setAreaName(area.getTitle());
        iExpressShelfService.saveOrUpdate(expressShelf);
        return new ResultUtil<ExpressShelf>().setData(expressShelf);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑快递货架")
    public Result<ExpressShelf> update(ExpressShelf expressShelf){
        ExpressArea area = iExpressAreaService.getById(expressShelf.getAreaId());
        if(area == null) {
            return ResultUtil.error("区域不存在");
        }
        expressShelf.setAreaName(area.getTitle());
        iExpressShelfService.saveOrUpdate(expressShelf);
        return new ResultUtil<ExpressShelf>().setData(expressShelf);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除快递货架")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iExpressShelfService.removeById(id);
        }
        return ResultUtil.success();
    }
}
