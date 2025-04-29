package cn.zwz.express.serviceimpl;

import cn.zwz.express.mapper.ExpressShelfMapper;
import cn.zwz.express.entity.ExpressShelf;
import cn.zwz.express.service.IExpressShelfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 快递货架 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IExpressShelfServiceImpl extends ServiceImpl<ExpressShelfMapper, ExpressShelf> implements IExpressShelfService {

    @Autowired
    private ExpressShelfMapper expressShelfMapper;
}