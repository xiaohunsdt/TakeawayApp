package net.novaborn.takeaway.activity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`activity`")
public class Activity extends Model<Activity> {
    private static final long serialVersionUID = 7938670865637915592L;

    @TableId(type = IdType.UUID)
    private String id;

    private String title;

    private String mainImg;

    private String content;

    private Date startDate;

    private Date endDate;

    private Boolean isShow;
}
