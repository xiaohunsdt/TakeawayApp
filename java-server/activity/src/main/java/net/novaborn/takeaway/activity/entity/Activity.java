package net.novaborn.takeaway.activity.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`activity`")
public class Activity extends Model<Activity> {
    private static final long serialVersionUID = 7938670865637915592L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String mainImg;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private Boolean isShow;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
