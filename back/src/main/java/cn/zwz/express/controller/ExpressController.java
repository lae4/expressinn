package cn.zwz.express.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.express.entity.Express;
import cn.zwz.express.entity.ExpressShelf;
import cn.zwz.express.service.IExpressService;
import cn.hutool.core.util.StrUtil;
import cn.zwz.express.service.IExpressShelfService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "快递管理接口")
@RequestMapping("/zwz/express")
@Transactional
public class ExpressController {

    @Autowired
    private IExpressService iExpressService;

    @Autowired
    private IExpressShelfService iExpressShelfService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条快递")
    public Result<Express> get(@RequestParam String id){
        return new ResultUtil<Express>().setData(iExpressService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iExpressService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部快递")
    public Result<List<Express>> getAll(){
        return new ResultUtil<List<Express>>().setData(iExpressService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询快递")
    public Result<IPage<Express>> getByPage(@ModelAttribute Express express ,@ModelAttribute PageVo page){
        QueryWrapper<Express> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND (role_id = '496138616573952' OR role_id = '1635124217320181760')");
        if(iUserService.count(userQw) < 1L) {
            userQw.and(wrapper -> wrapper.eq("send_mobile", currUser.getMobile()).or().eq("receiving_mobile",currUser.getMobile()));
        }
        if(!ZwzNullUtils.isNull(express.getShelfId())) {
            qw.eq("shelf_id",express.getShelfId());
        }
        if(!ZwzNullUtils.isNull(express.getAreaId())) {
            qw.eq("area_id",express.getAreaId());
        }
        if(!ZwzNullUtils.isNull(express.getReceivingUser())) {
            qw.like("receiving_user",express.getReceivingUser());
        }
        if(!ZwzNullUtils.isNull(express.getSendUser())) {
            qw.like("send_user",express.getSendUser());
        }
        IPage<Express> data = iExpressService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Express>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改快递")
    public Result<Express> saveOrUpdate(Express express){
        if(iExpressService.saveOrUpdate(express)){
            return new ResultUtil<Express>().setData(express);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增快递")
    public Result<Express> insert(Express express){
        ExpressShelf shelf = iExpressShelfService.getById(express.getShelfId());
        if(shelf == null) {
            return ResultUtil.error("货架不存在");
        }
        express.setShelfName(shelf.getTitle());
        express.setAreaId(shelf.getAreaId());
        express.setAreaName(shelf.getAreaName());
        iExpressService.saveOrUpdate(express);
        return new ResultUtil<Express>().setData(express);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑快递")
    public Result<Express> update(Express express){
        ExpressShelf shelf = iExpressShelfService.getById(express.getShelfId());
        if(shelf == null) {
            return ResultUtil.error("货架不存在");
        }
        express.setShelfName(shelf.getTitle());
        express.setAreaId(shelf.getAreaId());
        express.setAreaName(shelf.getAreaName());
        iExpressService.saveOrUpdate(express);
        return new ResultUtil<Express>().setData(express);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除快递")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iExpressService.removeById(id);
        }
        return ResultUtil.success();
    }
}
