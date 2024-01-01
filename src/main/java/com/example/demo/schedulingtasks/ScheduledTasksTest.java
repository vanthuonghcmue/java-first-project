package com.example.demo.schedulingtasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksTest {
//     Run every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void myTask() {
        System.out.println("Task executed at " + LocalDateTime.now());
    }

    // Run every day at 10 PM
    @Scheduled(cron = "0 9 8 * * ?")
    public void myCronJob() {
        System.out.println("Job executed at" +  LocalDateTime.now());
    }
}
