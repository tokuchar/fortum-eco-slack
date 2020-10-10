package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Slf4j
@Controller
@EnableScheduling
public class NotifyService {
    @Value("${queue.kettle}")
    private final String kettleQueue;
    private final RabbitTemplate rabbitTemplate;
    private final Map<String, DataGenerator> dataGenerators;

    public NotifyService(RabbitTemplate rabbitTemplate, @Value("${queue.kettle}") String kettleQueue, Map<String, DataGenerator> generatorMap) {
        this.kettleQueue = kettleQueue;
        this.rabbitTemplate = rabbitTemplate;
        this.dataGenerators = generatorMap;
    }

    @Scheduled(fixedRate = 1000)
    public void notifyAboutKettle() {
        for (DataGenerator generator:dataGenerators.values())
        {
            DeviceEvent event = generator.generate();
            log.info("send to " + kettleQueue + " message " + event.toString());
            rabbitTemplate.convertAndSend(kettleQueue, event);
        }
    }
}
