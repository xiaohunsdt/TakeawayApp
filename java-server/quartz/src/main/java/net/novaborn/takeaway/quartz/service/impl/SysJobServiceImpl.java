package net.novaborn.takeaway.quartz.service.impl;

import cn.hutool.core.convert.Convert;
import net.novaborn.takeaway.quartz.constant.ScheduleConstants;
import net.novaborn.takeaway.quartz.entity.SysJob;
import net.novaborn.takeaway.quartz.exception.TaskException;
import net.novaborn.takeaway.quartz.service.ISysJobService;
import net.novaborn.takeaway.quartz.util.CronUtils;
import net.novaborn.takeaway.quartz.util.ScheduleUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 * 
 * @author xiaohun
 */
@Service
public class SysJobServiceImpl implements ISysJobService
{
    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器 
	 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException
    {
        SysJob sysJob = new SysJob();
        sysJob.setCronExpression("0/5 * * * * ?");
        sysJob.setInvokeTarget("ryTask.ryParams('asdasd')");
        sysJob.setStatus(ScheduleConstants.Status.NORMAL);
        sysJob.setConcurrent(false);
        ScheduleUtils.createScheduleJob(scheduler, sysJob);
    }

    /**
     * 获取quartz调度器的计划任务列表
     * 
     * @param job 调度信息
     * @return
     */
    @Override
    public List<SysJob> selectJobList(SysJob job)
    {
//        return jobMapper.selectJobList(job);
        return null;
    }

    /**
     * 通过调度任务ID查询调度信息
     * 
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public SysJob selectJobById(Long jobId)
    {
//        return jobMapper.selectJobById(jobId);
        return null;
    }

    /**
     * 暂停任务
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional
    public int pauseJob(SysJob job) throws SchedulerException
    {
//        Long jobId = job.getJobId();
//        String jobGroup = job.getJobGroup();
//        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
//        int rows = jobMapper.updateJob(job);
//        if (rows > 0)
//        {
//            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
//        }
//        return rows;
        return 0;
    }

    /**
     * 恢复任务
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional
    public int resumeJob(SysJob job) throws SchedulerException
    {
//        Long jobId = job.getJobId();
//        String jobGroup = job.getJobGroup();
//        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
//        int rows = jobMapper.updateJob(job);
//        if (rows > 0)
//        {
//            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
//        }
//        return rows;
        return 0;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional
    public int deleteJob(SysJob job) throws SchedulerException
    {
//        Long jobId = job.getJobId();
//        String jobGroup = job.getJobGroup();
//        int rows = jobMapper.deleteJobById(jobId);
//        if (rows > 0)
//        {
//            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
//        }
//        return rows;
        return 0;
    }

    /**
     * 批量删除调度信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public void deleteJobByIds(String ids) throws SchedulerException
    {
//        Long[] jobIds = Convert.toLongArray(ids);
//        for (Long jobId : jobIds)
//        {
//            SysJob job = jobMapper.selectJobById(jobId);
//            deleteJob(job);
//        }
    }

    /**
     * 任务调度状态修改
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional
    public int changeStatus(SysJob job) throws SchedulerException
    {
        int rows = 0;
        ScheduleConstants.Status status = job.getStatus();
        if (status == ScheduleConstants.Status.NORMAL)
        {
            rows = resumeJob(job);
        } else if (status == ScheduleConstants.Status.PAUSE) {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * 立即运行任务
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional
    public void run(SysJob job) throws SchedulerException
    {
        Long jobId = job.getJobId();
        SysJob tmpObj = selectJobById(job.getJobId());
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, tmpObj);
        scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, tmpObj.getJobGroup()), dataMap);
    }

    /**
     * 新增任务
     * 
     * @param job 调度信息 调度信息
     */
    @Override
    @Transactional
    public int insertJob(SysJob job) throws SchedulerException, TaskException
    {
//        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
//        int rows = jobMapper.insertJob(job);
//        if (rows > 0)
//        {
//            ScheduleUtils.createScheduleJob(scheduler, job);
//        }
//        return rows;
        ScheduleUtils.createScheduleJob(scheduler, job);
        return 0;
    }

    /**
     * 更新任务的时间表达式
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional
    public int updateJob(SysJob job) throws SchedulerException, TaskException
    {
//        SysJob properties = selectJobById(job.getJobId());
//        int rows = jobMapper.updateJob(job);
//        if (rows > 0)
//        {
//            updateSchedulerJob(job, properties.getJobGroup());
//        }
        updateSchedulerJob(job, job.getJobGroup());
        return 0;
    }

    /**
     * 更新任务
     * 
     * @param job 任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException
    {
        Long jobId = job.getJobId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey))
        {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * 校验cron表达式是否有效
     * 
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression)
    {
        return CronUtils.isValid(cronExpression);
    }
}