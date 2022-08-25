package com.devjck.springboard.config.schedule;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.enumType.Authority;
import com.devjck.springboard.domain.user.enumType.Status;
import com.devjck.springboard.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTask {
    @Autowired
    private UserService userService;

    private LocalDateTime currentTime = LocalDateTime.now();
    private LocalDateTime userLastAccessTime;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0 0 1/1 * *")
    public void checkDormantAccount() {
        List<User> users = userService.getAllUsers();
        if (users == null || users.size() == 0) LOGGER.info("user is empty..");
        else {
            LOGGER.info("start midnight check dormancy user ");
            users.stream().forEach(user -> {
                userLastAccessTime = user.getLastAccessTime();
                userLastAccessTime = LocalDateTime.of(userLastAccessTime.getYear(), userLastAccessTime.getMonth(), userLastAccessTime.getDayOfMonth(),
                        userLastAccessTime.getHour(), userLastAccessTime.getMinute());

                currentTime = LocalDateTime.of(currentTime.getYear(), currentTime.getMonth(), currentTime.getDayOfMonth(),
                        currentTime.getHour(), currentTime.getMinute());
                Long differenceDay = Duration.between(userLastAccessTime, currentTime).toDays();
                if (differenceDay > 0) {
                    if (!user.getAuthority().equals(Authority.ADMIN) && user.getStatus().equals(Status.NORMAL)) {
                        userService.updateStatus(Status.DORMANCY.getValue(), user.getUserId());
                    }
                }
            });
        }
    }
}
