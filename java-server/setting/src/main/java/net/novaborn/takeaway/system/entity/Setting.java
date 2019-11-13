package net.novaborn.takeaway.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`setting`")
public class Setting extends Model<Setting> {
    private static final long serialVersionUID = -1365755510215805455L;

    @NotEmpty(message = "名称不能为空")
    @TableId(type = IdType.INPUT)
    private String key;

    @NotEmpty(message = "设置值不能为空")
    private String value;
}
