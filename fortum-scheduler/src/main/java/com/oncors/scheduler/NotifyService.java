package com.oncors.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@EnableScheduling
public class NotifyService {
    @Value("${queue.kettle}")
    private final String kettleQueue;
    private final RabbitTemplate rabbitTemplate;

    public NotifyService(RabbitTemplate rabbitTemplate, @Value("${queue.kettle}") String kettleQueue) {
        this.kettleQueue = kettleQueue;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void notifyAboutKettle() {
        String message = "test";
        log.info("send to " + kettleQueue + " message " + message);
        rabbitTemplate.convertAndSend(kettleQueue, message);
    }
}
