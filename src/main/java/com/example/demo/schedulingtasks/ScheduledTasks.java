package com.example.demo.schedulingtasks;

import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasks {
    final
    UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    public ScheduledTasks(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Run every 5 seconds
    @Scheduled(fixedRate = 50000)
    public void countTotalUsersTask() {
        LOGGER.info("Task executed at {}", LocalDateTime.now());
        long userCount = userRepository.count();
        LOGGER.info("Total users = {}", userCount);
    }

    // Run every day at 10 PM
    @Scheduled(cron = "0 0 22 * * ?")
    public void myCronJob() {
        LOGGER.info("Job executed at {}", LocalDateTime.now());
    }
}
