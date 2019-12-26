package com.example.demo.service.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        String jobParameter=shardingContext.getJobParameter();

        System.out.println(jobParameter+"  "+"开始运行"+System.currentTimeMillis());
    }
}
