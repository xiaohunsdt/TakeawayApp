package net.novaborn.takeaway.order.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * 订单的评论类
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`comment`")
public class Comment extends Model<Comment> {
    private static final long serialVersionUID = 8975431669565356354L;

    @TableId(type = IdType.UUID)
    private String id;

    private String userId;

    private String orderId;

    /**
     * 菜品口味
     */
    @Min(value = 1, message = "请评价口味")
    private Integer delicious;

    /**
     * 配送速度
     */
    @Min(value = 1, message = "请评价配送")
    private Integer express;

    /**
     * 服务态度
     */
    @Min(value = 1, message = "请评价服务")
    private Integer service;

    private String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
