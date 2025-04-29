package cn.zwz.express.serviceimpl;

import cn.zwz.express.mapper.ExpressAreaMapper;
import cn.zwz.express.entity.ExpressArea;
import cn.zwz.express.service.IExpressAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 快递区域 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IExpressAreaServiceImpl extends ServiceImpl<ExpressAreaMapper, ExpressArea> implements IExpressAreaService {

    @Autowired
    private ExpressAreaMapper expressAreaMapper;
}