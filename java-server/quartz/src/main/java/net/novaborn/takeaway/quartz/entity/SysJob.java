package net.novaborn.takeaway.quartz.entity;

import lombok.Data;
import net.novaborn.takeaway.quartz.constant.ScheduleConstants;
import net.novaborn.takeaway.quartz.util.CronUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度表 sys_job
 *
 * @author xiaohun
 */
@Data
public class SysJob implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private Long jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组名
     */
    private String jobGroup;

    /**
     * 调用目标字符串
     */
    private String invokeTarget;

    /**
     * cron执行表达式
     */
    private String cronExpression;

    /**
     * cron计划策略
     */
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /**
     * 是否并发执行（0允许 1禁止）
     */
    private Boolean concurrent;

    /**
     * 任务状态（0正常 1暂停）
     */
    private ScheduleConstants.Status status;

    public Date getNextValidTime() {
        if (!cronExpression.isEmpty()) {
            return CronUtils.getNextExecution(cronExpression);
        }
        return null;
    }
}