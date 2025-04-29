package cn.zwz.express.serviceimpl;

import cn.zwz.express.mapper.PackageTypeMapper;
import cn.zwz.express.entity.PackageType;
import cn.zwz.express.service.IPackageTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 快递包装类型 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IPackageTypeServiceImpl extends ServiceImpl<PackageTypeMapper, PackageType> implements IPackageTypeService {

    @Autowired
    private PackageTypeMapper packageTypeMapper;
}