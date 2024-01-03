package com.example.demo.schedulingtask;

import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    final
    UserRepository userRepository;

    private final RedisTemplate redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    public ScheduledTasks(UserRepository userRepository, RedisTemplate redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    // Run every 5 seconds
    @Scheduled(fixedRate = 50000)
    public void countTotalUsersTask() {
        LOGGER.info("Task executed at {}", LocalDateTime.now());
        long totalUser = userRepository.count();
        redisTemplate.opsForValue().set("totalUser", totalUser, 1, TimeUnit.DAYS);
        LOGGER.info("Total users = {}", redisTemplate.opsForValue().get("totalUser"));
    }

    // Run every day at 10 PM
    @Scheduled(cron = "0 0 22 * * ?")
    public void myCronJob() {
        LOGGER.info("Job executed at {}", LocalDateTime.now());
    }
}
