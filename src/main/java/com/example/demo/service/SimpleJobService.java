package com.example.demo.service;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.example.demo.service.job.MySimpleJob;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SimpleJobService {

    @Resource
    private CoordinatorRegistryCenter registryCenter;

    private AtomicInteger integer=new AtomicInteger(0);

    public void doJob(String jobParameter){
        JobCoreConfiguration jobCoreConfiguration= JobCoreConfiguration
                .newBuilder("hello-job",initCron(),1)
                .jobParameter(jobParameter)
                .jobProperties("job-instance-id",integer.getAndIncrement()+"")
                .build();

        SimpleJobConfiguration simpleJobConfiguration=new SimpleJobConfiguration(jobCoreConfiguration, MySimpleJob.class.getCanonicalName());
        LiteJobConfiguration liteJobConfiguration=LiteJobConfiguration
                .newBuilder(simpleJobConfiguration)
                .overwrite(true)
                .build();

        new JobScheduler(registryCenter,liteJobConfiguration).init();
    }

    public String initCron(){
        LocalDateTime delayTime=LocalDateTime.now().plus(10, ChronoUnit.SECONDS);

        int second=delayTime.getSecond();
        int minute=delayTime.getMinute();
        int hour=delayTime.getHour();

        int day=delayTime.getDayOfMonth();
        int month=delayTime.getMonthValue();

        return second + " " + minute + " " + hour + " " + day + " " + month + " ?";
    }
}
