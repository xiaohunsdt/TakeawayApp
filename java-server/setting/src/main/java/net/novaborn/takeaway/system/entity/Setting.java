package net.novaborn.takeaway.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.system.enums.SettingScope;

import javax.validation.constraints.NotEmpty;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`setting`")
public class Setting extends Model<Setting> {
    private static final long serialVersionUID = -1365755510215805455L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @NotEmpty(message = "名称不能为空")
    @TableField(value = "`key`")
    private String key;

    @NotEmpty(message = "域不能为空")
    @TableField(value = "`scope`")
    private SettingScope scope;

    @NotEmpty(message = "设置值不能为空")
    private String value;
}
