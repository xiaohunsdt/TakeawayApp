package net.novaborn.takeaway.banner.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
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
@TableName("`banner`")
public class Banner extends Model<Banner> {
    private static final long serialVersionUID = 7938670865637115592L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "图片不能为空")
    private String img;

    private String pagePath;

    private Boolean isShow;

    @TableField(value = "`index`")
    private Integer index;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
