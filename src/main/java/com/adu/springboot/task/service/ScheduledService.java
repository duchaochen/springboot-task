package com.adu.springboot.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     * 表达式
     *  second(秒) minute（分）, hour（时）, day of month（日）, month（月） day of week（周）
     *  示例：{@code "0 * * * * MON-FRI"}
     * 表示从星期1到星6，每分钟的1，2，3，4秒就启动
     * @Scheduled(cron = "1,2,3,4 * * * * MON-SAT")
     */
    @Scheduled(cron = "0-59 9 13 13 * *")
    public void show() {
        System.out.println("hello.....");
    }
}
