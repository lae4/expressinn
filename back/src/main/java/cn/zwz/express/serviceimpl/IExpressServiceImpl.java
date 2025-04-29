package cn.zwz.express.serviceimpl;

import cn.zwz.express.mapper.ExpressMapper;
import cn.zwz.express.entity.Express;
import cn.zwz.express.service.IExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 快递 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements IExpressService {

    @Autowired
    private ExpressMapper expressMapper;
}