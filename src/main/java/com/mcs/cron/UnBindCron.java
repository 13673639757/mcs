package com.mcs.cron;

import com.mcs.service.CarLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @ClassName
 * @Description 定时解绑号码
 * @Author szq
 * @Date 2019/8/3 20:34
 * @Version 1.0
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class UnBindCron {
    private static final Logger log= LoggerFactory.getLogger(UnBindCron.class);
    @Resource
    private CarLinkService carLinkService;
    //3.添加定时任务
    @Scheduled(cron = "0 0/2 * * * ? ")
    public void configureTasks() {
        log.info("执行解绑定时任务时间: " + LocalDateTime.now());
        carLinkService.axUnbindNumber();

    }
}
