package com.example.demo.controller;

import com.example.demo.service.SimpleJobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private SimpleJobService simpleJobService;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name){
        System.out.println("开始调用任务："+System.currentTimeMillis());
        simpleJobService.doJob(name);

        return "success";
    }
}
