package cn.zwz.express.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_express")
@TableName("a_express")
@ApiModel(value = "快递")
public class Express extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属货架ID")
    private String shelfId;

    @ApiModelProperty(value = "所属货架")
    private String shelfName;

    @ApiModelProperty(value = "所属区域ID")
    private String areaId;

    @ApiModelProperty(value = "所属区域")
    private String areaName;

    @ApiModelProperty(value = "快递类型")
    private String expressType;

    @ApiModelProperty(value = "包装类型")
    private String packageType;

    @ApiModelProperty(value = "收件人")
    private String receivingUser;

    @ApiModelProperty(value = "收件人电话")
    private String receivingMobile;

    @ApiModelProperty(value = "收件地址")
    private String receivingAddress;

    @ApiModelProperty(value = "发件人")
    private String sendUser;

    @ApiModelProperty(value = "发件人电话")
    private String sendMobile;

    @ApiModelProperty(value = "发件地址")
    private String sendAddress;

    @ApiModelProperty(value = "快递状态")
    private String status;
}