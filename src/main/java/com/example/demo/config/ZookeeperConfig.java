package com.example.demo.config;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.connecting-string}")
    private String connectingString;

    @Bean
    public CoordinatorRegistryCenter initCoordinatorRegistryCenter(){
        ZookeeperConfiguration zookeeperConfiguration=new ZookeeperConfiguration(connectingString,"elastic-job");
        CoordinatorRegistryCenter coordinatorRegistryCenter=new ZookeeperRegistryCenter(zookeeperConfiguration);
        coordinatorRegistryCenter.init();

        return coordinatorRegistryCenter;
    }
}
