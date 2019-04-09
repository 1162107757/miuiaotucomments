package top.hjie.quartz;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import top.hjie.task.AutoComment;

@Configuration
public class QuartzConfiguration {

    @Bean(name = "secondJobDetail")
    public MethodInvokingJobDetailFactoryBean secondJobDetail(AutoComment autoComment) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(autoComment);
        // 需要执行的方法
        jobDetail.setTargetMethod("assignmentAask");
        return jobDetail;
    }

    @Bean(name = "secondTrigger")
    public CronTriggerFactoryBean secondTrigger(JobDetail secondJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(secondJobDetail); 
        // cron表达式 
	    trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }
    
    // 配置Scheduler
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger firstTrigger, Trigger secondTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();  
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);  
        // 注册触发器
        bean.setTriggers(firstTrigger,secondTrigger);
        return bean;
    }
	
}
